package com.example.iss_ltbtp.Domain.Repository;

import com.example.iss_ltbtp.Data.Entity.Bug;

import java.util.List;

public interface IBugRepository {

    void insert(final Bug bug);

    void update(final Bug bug);

    List<Bug> findAll();
}
