package com.hotel.app.service;

import com.hotel.app.exception.CouldNotFindException;
import com.hotel.app.model.Traveler;
import com.hotel.app.repository.TravelerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TravelerServiceImpl implements  TravelerService {

    @Autowired
    private TravelerRepository repository;

    @Override
    public List<Traveler> getListOfTravelers() {
        return repository.findAll();
    }

    @Override
    public Traveler getTravelerById(Long travelerId) {
        return repository.findById(travelerId)
                .orElseThrow(() -> new CouldNotFindException("traveler with id " +travelerId));
    }

    @Override
    public Traveler addOrUpdateTraveler(Traveler traveler) {
        return repository.save(traveler);
    }

    @Override
    public void deleteTraveler(Long travelerId) {
        repository.delete(getTravelerById(travelerId));
    }
}
