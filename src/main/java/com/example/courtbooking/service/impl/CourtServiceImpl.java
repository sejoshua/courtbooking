package com.example.courtbooking.service.impl;

import com.example.courtbooking.config.ApplicationConfig;
import com.example.courtbooking.model.impl.Court;
import com.example.courtbooking.repo.DataRepo;
import com.example.courtbooking.service.CourtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author Joshua Xing
 */
@Service
public class CourtServiceImpl implements CourtService {
    private final DataRepo<Court> repo;

    @Autowired
    public CourtServiceImpl(@Qualifier(ApplicationConfig.COURT_REPO_NAME) DataRepo<Court> repo) {
        this.repo = repo;
    }

    @Override
    public long createCourt(String name) {
        Court court = new Court();
        court.setName(name);
        return repo.addData(court);
    }

    @Override
    public Court getCourt(long id) {
        return repo.getData(id);
    }
}
