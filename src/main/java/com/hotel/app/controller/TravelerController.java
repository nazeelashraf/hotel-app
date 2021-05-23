package com.hotel.app.controller;

import com.hotel.app.model.Traveler;
import com.hotel.app.service.TravelerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("traveler")
public class TravelerController {

    @Autowired
    TravelerService service;

    @GetMapping
    List<Traveler> getTravelers() {
        return service.getListOfTravelers();
    }

    @GetMapping("{travelerId}")
    Traveler getTraveler(@PathVariable Long travelerId) {
        return service.getTravelerById(travelerId);
    }

    @PostMapping
    Traveler addOrUpdateTraveler(@RequestBody Traveler traveler) {
        return service.addOrUpdateTraveler(traveler);
    }

    @DeleteMapping("{travelerId}")
    public void deleteTraveler(@PathVariable Long travelerId){
        service.deleteTraveler(travelerId);
    }
}
