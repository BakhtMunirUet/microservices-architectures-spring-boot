package com.example.userservice.services.impl;

import com.example.userservice.entities.Hotel;
import com.example.userservice.entities.Rating;
import com.example.userservice.entities.User;
import com.example.userservice.exceptions.ResourceNotFoundException;
import com.example.userservice.external.services.HotelService;
import com.example.userservice.repositories.UserRepository;
import com.example.userservice.services.UserService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private HotelService hotelService;

  private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

  @Override
  public User saveUser(User user) {
    //generate  unique userid
    String randomUserId = UUID.randomUUID().toString();
    user.setUserId(randomUserId);
    return userRepository.save(user);
  }

  @Override
  public List<User> getAllUser() {
    //implement RATING SERVICE CALL: USING REST TEMPLATE
    return userRepository.findAll();
  }

  //get single user
  @Override
  public User getUser(String userId) {
    //get user from database with the help  of user repository
    User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server !! : " + userId));
    // fetch rating of the above  user from RATING SERVICE
    //http://localhost:8083/ratings/users/47e38dac-c7d0-4c40-8582-11d15f185fad

    Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(),
        Rating[].class);
    logger.info("{} ", ratingsOfUser);

//    List<Rating> ratingListFrist = Arrays.stream(ratingsOfUser).toList()
    List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();
    List<Rating> ratingList = ratings.stream().map(rating -> {
      //api call to hotel service to get the hotel
      //http://localhost:8082/hotels/1cbaf36d-0b28-4173-b5ea-f1cb0bc0a791
//      ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
      Hotel hotel = hotelService.getHotel(rating.getHotelId());
      // logger.info("response status code: {} ",forEntity.getStatusCode());
      //set the hotel to rating
      rating.setHotel(hotel);
      //return the rating
      return rating;
    }).collect(Collectors.toList());
//
    user.setRatings(ratingList);

    return user;
  }
}
