package com.hotel.app.service;

import com.hotel.app.model.Traveler;

import java.util.List;

public interface TravelerService {
    List<Traveler> getListOfTravelers();
    Traveler getTravelerById(Long travelerId);
    Traveler addOrUpdateTraveler(Traveler traveler);
    void deleteTraveler(Long travelerId);
}
