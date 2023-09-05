package com.example.ratingservice.repositories;

import com.example.ratingservice.entities.Rating;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RatingRepository extends MongoRepository<Rating,String>
{
  //custom finder methods
  List<Rating> findByUserId(String userId);
  List<Rating> findByHotelId(String hotelId);

}
