package com.example.iss_ltbtp.Data.Validation;

import com.example.iss_ltbtp.Data.Entity.User;
import com.example.iss_ltbtp.Utils.Constants;

public class UserValidator implements Validator<User> {

    @Override
    public void validate(final User user) throws Exception {
        if (Constants.Length.Min.USERNAME > user.getName().length()) {
            throw new Exception("invalid username length");
        }

        if (Constants.Length.Min.PASSWORD > user.getPassword().length()) {
            throw new Exception("invalid password length");
        }

        if (User.Type.NONE == user.getType()) {
            throw new Exception("invalid user type (none)");
        }
    }
}
