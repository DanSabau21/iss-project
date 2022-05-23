package com.example.iss_ltbtp.Utils;

import org.hibernate.Session;
import org.hibernate.Transaction;

@FunctionalInterface
public interface TransactionConsumer <T> {

    T consume(final Session session, final Transaction transaction);
}
