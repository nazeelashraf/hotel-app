package com.hotel.app.service;


import com.hotel.app.bean.SearchRequest;
import com.hotel.app.exception.CouldNotFindException;
import com.hotel.app.model.Hotel;
import com.hotel.app.model.Room;

import java.util.List;

public interface HotelService {
    List<Hotel> getAllHotels();
    Hotel getHotelById(Long id) throws CouldNotFindException;
    List<Room> getAllHotelsByCriteria(SearchRequest request);

    Hotel addOrUpdate(Hotel hotel) throws CouldNotFindException;

    void deleteHotel(Long hotelId);
}
