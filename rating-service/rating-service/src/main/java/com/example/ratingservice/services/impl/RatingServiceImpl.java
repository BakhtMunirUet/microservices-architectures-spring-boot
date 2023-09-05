package com.example.ratingservice.services.impl;

import com.example.ratingservice.entities.Rating;
import com.example.ratingservice.repositories.RatingRepository;
import com.example.ratingservice.services.RatingService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl implements RatingService {


  @Autowired
  private RatingRepository repository;

  @Override
  public Rating create(Rating rating) {
    return repository.save(rating);
  }

  @Override
  public List<Rating> getRatings() {
    return repository.findAll();
  }

  @Override
  public List<Rating> getRatingByUserId(String userId) {
    return repository.findByUserId(userId);
  }

  @Override
  public List<Rating> getRatingByHotelId(String hotelId) {
    return repository.findByHotelId(hotelId);
  }
}

