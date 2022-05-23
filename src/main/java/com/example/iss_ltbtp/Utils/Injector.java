package com.example.iss_ltbtp.Utils;

import com.example.iss_ltbtp.Data.Entity.User;
import com.example.iss_ltbtp.Data.Repository.BugRepository;
import com.example.iss_ltbtp.Data.Validation.BugValidator;
import com.example.iss_ltbtp.Domain.Repository.IBugRepository;
import com.example.iss_ltbtp.Domain.Repository.IUserRepository;
import com.example.iss_ltbtp.Data.Validation.UserValidator;
import com.example.iss_ltbtp.Data.Repository.UserRepository;
import com.example.iss_ltbtp.Domain.Service.BugService;
import com.example.iss_ltbtp.Domain.Service.UserService;

public final class Injector {

    private static User newestLoggedInUser;

    public static User getNewestLoggedInUser() {
        return newestLoggedInUser;
    }

    public static void setNewestLoggedInUser(final User user) {
        Injector.newestLoggedInUser = user;
    }

    public static final UserService userService;
    public static final BugService bugService;

    static {
        // user
        final IUserRepository userRepository = new UserRepository();
        final UserValidator userValidator =  new UserValidator();

        userService = new UserService(userRepository, userValidator);

        // bug
        final IBugRepository bugRepository = new BugRepository();
        final BugValidator bugValidator = new BugValidator();

        bugService = new BugService(bugRepository, bugValidator);
    }
}
