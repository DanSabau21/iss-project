package com.example.iss_ltbtp.Data.Repository;

import com.example.iss_ltbtp.Data.Entity.User;
import com.example.iss_ltbtp.Domain.Repository.IUserRepository;
import com.example.iss_ltbtp.Utils.Hibernate;

import java.util.Optional;

public class UserRepository implements IUserRepository {

    @Override
    public void insert(final User user) {
        Hibernate.modify((session, transaction) -> session.save(user));
    }

    @Override
    public Optional<User> findByUsername(final String username) {
        return Hibernate.getSafeResult(session -> {
            final User user = session
                    .createQuery("from User u where u.name = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();
            return user == null
                    ? Optional.empty()
                    : Optional.of(user);
        }, Optional.empty());
    }
}
