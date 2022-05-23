package com.example.iss_ltbtp.Domain.Service;

import com.example.iss_ltbtp.Data.Entity.Bug;
import com.example.iss_ltbtp.Data.Validation.BugValidator;
import com.example.iss_ltbtp.Domain.Repository.IBugRepository;
import com.example.iss_ltbtp.Utils.Observable;

import java.util.List;
import java.util.Random;

public record BugService(IBugRepository bugRepository, BugValidator bugValidator) implements Observable {
    private static final Random random = new Random();

    public Bug insert(final String description) throws Exception {
        final Bug bug = new Bug(
                random.nextInt(),
                description,
                Bug.Status.UNSOLVED
        );

        bugValidator.validate(bug);
        bugRepository.insert(bug);

        notifyUpdate();

        return bug;
    }

    public void update(final Bug bug) {
        bugRepository.update(bug);
        notifyUpdate();
    }

    public List<Bug> findAll() {
        return bugRepository.findAll();
    }
}
