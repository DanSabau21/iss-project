package com.example.iss_ltbtp.Data.Repository;

import com.example.iss_ltbtp.Data.Entity.Bug;
import com.example.iss_ltbtp.Domain.Repository.IBugRepository;
import com.example.iss_ltbtp.Utils.Hibernate;

import java.util.Collections;
import java.util.List;

public class BugRepository implements IBugRepository {

    @Override
    public void insert(final Bug bug) {
        Hibernate.modify((session, transaction) -> session.save(bug));
    }

    @Override
    public void update(final Bug bug) {
        Hibernate.modify((session, transaction) -> {
            session.update(bug);
            return null;
        });
    }

    @Override
    public List<Bug> findAll() {
        return Hibernate.getSafeResult(session ->
                session.createQuery("from Bug", Bug.class).getResultList(),
                Collections.emptyList()
        );
    }
}
