package com.example.iss_ltbtp.Domain.Service;

import com.example.iss_ltbtp.Data.Entity.User;
import com.example.iss_ltbtp.Domain.Repository.IUserRepository;
import com.example.iss_ltbtp.Data.Validation.UserValidator;

import java.util.Objects;
import java.util.Optional;
import java.util.Random;

public record UserService(IUserRepository userRepository, UserValidator userValidator) {

    private static final Random random = new Random();

    public User insert(final String username,
                       final String password,
                       final User.Type type) throws Exception {
        final User user = new User(
                random.nextInt(),
                username,
                password,
                type
        );

        userValidator.validate(user);
        userRepository.insert(user);
        return user;
    }

    public Optional<User> findByCredentials(final String username, final String password) {
        final Optional<User> optional = userRepository.findByUsername(username);
        if (optional.isPresent()) {
            final User user = optional.get();
            if (!Objects.equals(user.getPassword(), password)) {
                return Optional.empty();
            }
        }
        return optional;
    }
}
