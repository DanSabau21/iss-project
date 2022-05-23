package com.example.iss_ltbtp.Domain.Repository;

import com.example.iss_ltbtp.Data.Entity.User;

import java.util.Optional;

public interface IUserRepository {

    void insert(final User user);

    Optional<User> findByUsername(final String username);
}
