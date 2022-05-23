package com.example.iss_ltbtp.Data.Validation;

public interface Validator <T> {

    void validate(final T obj) throws Exception;
}
