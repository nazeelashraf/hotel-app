package com.hotel.app.controller;

import com.hotel.app.bean.SearchRequest;
import com.hotel.app.exception.CouldNotFindException;
import com.hotel.app.model.Hotel;
import com.hotel.app.model.Room;
import com.hotel.app.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("hotel")
public class HotelController {

    @Autowired
    HotelService service;

    @GetMapping
    public List<Hotel> fetchHotels() {
        return service.getAllHotels();
    }

    @GetMapping("{id}")
    public Hotel fetchHotel(@PathVariable Long id) throws CouldNotFindException {
        return service.getHotelById(id);
    }

    @PostMapping("search")
    public List<Room> getAllHotelsByCriteria(@RequestBody SearchRequest request) {
        return service.getAllHotelsByCriteria(request);
    }

    @PostMapping
    public Hotel addorUpdateHotel(@RequestBody Hotel hotel) {
        return service.addOrUpdate(hotel);
    }

    @DeleteMapping("{hotelId}")
    public void deleteHotel(@PathVariable Long hotelId){
        service.deleteHotel(hotelId);
    }
}
