package com.example.hotelservice.services;

import com.example.hotelservice.entities.Hotel;
import java.util.List;

public interface HotelService {

  //create

  Hotel create(Hotel hotel);

  //get all
  List<Hotel> getAll();

  //get single
  Hotel get(String id);

}