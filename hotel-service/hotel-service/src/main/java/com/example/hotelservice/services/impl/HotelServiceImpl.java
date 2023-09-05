package com.example.hotelservice.services.impl;

import com.example.hotelservice.entities.Hotel;
import com.example.hotelservice.exceptions.ResourceNotFoundException;
import com.example.hotelservice.repositories.HotelRepository;
import com.example.hotelservice.services.HotelService;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelServiceImpl implements HotelService {

  @Autowired()
  private HotelRepository hotelRepository;

  @Override
  public Hotel create(Hotel hotel) {
    String hotelId = UUID.randomUUID().toString();
    hotel.setId(hotelId);
    return hotelRepository.save(hotel);
  }

  @Override
  public List<Hotel> getAll() {
    return hotelRepository.findAll();
  }

  @Override
  public Hotel get(String id) {
    return hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("hotel with given id not found !!"));
  }
}

