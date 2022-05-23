package com.example.iss_ltbtp.Data.Validation;

import com.example.iss_ltbtp.Data.Entity.Bug;

public class BugValidator implements Validator<Bug> {

    @Override
    public void validate(final Bug bug) throws Exception {
        if (bug.getDescription().isBlank()) {
            throw new Exception("the bug description cannot be blank!");
        }
    }
}
