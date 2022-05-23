package com.example.iss_ltbtp.Utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public final class Hibernate {

    private static final SessionFactory SESSION_FACTORY;

    static {
        final StandardServiceRegistry standardServiceRegistry =
                new StandardServiceRegistryBuilder()
                        .configure("hibernate.cfg.xml").build();
        final MetadataSources metadataSources = new MetadataSources(standardServiceRegistry);
        final Metadata metadata = metadataSources.getMetadataBuilder().build();
        SESSION_FACTORY = metadata.getSessionFactoryBuilder().build();
    }

    public static void modify(final TransactionConsumer<?> consumer) {
        getSafeResult(session -> {
            final Transaction transaction = session.beginTransaction();
            consumer.consume(session, transaction);

            transaction.commit();

            return null;
        }, null);
    }

    public static <T> T getSafeResult(final SessionConsumer<T> consumer,
                                      final T errorCaseValue) {
        try (final Session session = SESSION_FACTORY.openSession()) {
            return consumer.consume(session);
        } catch (final Exception e) {
            return errorCaseValue;
        }
    }
}
