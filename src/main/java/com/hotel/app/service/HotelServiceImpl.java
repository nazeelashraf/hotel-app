package com.hotel.app.service;

import com.hotel.app.bean.SearchRequest;
import com.hotel.app.exception.CouldNotFindException;
import com.hotel.app.model.Hotel;
import com.hotel.app.model.Room;
import com.hotel.app.repository.HotelRepository;
import com.hotel.app.repository.RoomRepository;
import com.hotel.app.util.SpecificationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    SpecificationUtil specificationUtil;

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotelById(Long id) throws CouldNotFindException {
        return hotelRepository.findById(id)
                .orElseThrow(()-> new CouldNotFindException("Hotel"));
    }

    @Override
    public List<Room> getAllHotelsByCriteria(SearchRequest request) {
        Specification specification = specificationUtil.andAll(request.getCriteria());
        Sort sort = Sort.by("hotel.rating").descending().and(Sort.by("pricePerNight").descending());
        if(request.getSortBy()!=null)
            sort = Sort.by(request.getSortBy());
        if(!request.getAscending()) sort = sort.descending();

        PageRequest pageRequest = PageRequest.of(request.getPageNumber(), request.getPageItems(), sort);

        if(specification==null) return roomRepository.findAll();
        return roomRepository.findAll(specification, pageRequest).toList();
    }

    @Override
    public Hotel addOrUpdate(Hotel hotel) throws CouldNotFindException {
        return hotelRepository.save(hotel);
    }

    @Override
    public void deleteHotel(Long hotelId) {
        hotelRepository.delete(getHotelById(hotelId));
    }
}
