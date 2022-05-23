package com.example.iss_ltbtp.Utils;

import org.hibernate.Session;

@FunctionalInterface
public interface SessionConsumer<T> {

    T consume(final Session session);
}
