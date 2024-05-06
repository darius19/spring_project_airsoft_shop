package com.airsoftShop.airsoftShop.service;

import com.airsoftShop.airsoftShop.dto.UserAuthDto;
import com.airsoftShop.airsoftShop.dto.UserProfileDto;
import com.airsoftShop.airsoftShop.dto.UserRegistrationDto;
import com.airsoftShop.airsoftShop.model.User;
import com.airsoftShop.airsoftShop.repository.UserRepository;
import com.airsoftShop.airsoftShop.util.DefaultValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public boolean saveUser(UserRegistrationDto userRegistrationDto) {
        if (userRepository.findByEmail(userRegistrationDto.getEmail()).isEmpty() && userRepository.findByUsername(userRegistrationDto.getUsername()).isEmpty()) {
            User user = new User();
            user.setEmail(userRegistrationDto.getEmail());
            user.setUsername(userRegistrationDto.getUsername());
            String password = passwordEncoder.encode(userRegistrationDto.getPassword());
            user.setPassword(password);
            user.setRole(DefaultValues.defaultRegistrationRole);
            userRepository.save(user);
            return true;

        }
        return false;
    }

    public void updateUser(UserProfileDto userProfileDto) {
        User user = userRepository.findById(Long.valueOf(userProfileDto.getId())).orElseThrow();

        user.setUsername(userProfileDto.getUsername());
//

        user.setFirstName(userProfileDto.getFirstName());


        user.setLastName(userProfileDto.getLastName());


        user.setPhoneNumber(userProfileDto.getPhoneNumber());


        user.setAddress(userProfileDto.getAddress());


        user.setCity(userProfileDto.getCity());


        user.setCountry(userProfileDto.getCountry());


        user.setPostalCode(userProfileDto.getPostalCode());


        try {
            user.setDob(LocalDateTime.parse(userProfileDto.getDob()));
        } catch (DateTimeParseException dateTimeParseException){
            user.setDob(null);
        }


        userRepository.save(user);


    }


    public UserProfileDto getUserProfileDto(String email) {
        User user = userRepository.findByEmail(email).orElseThrow();
        UserProfileDto result = new UserProfileDto();
        result.setAddress(user.getAddress());
        result.setCity(user.getCity());
        result.setDob(String.valueOf(user.getDob()));
        result.setId(String.valueOf(user.getId()));
        result.setFirstName(user.getFirstName());
        result.setLastName(user.getLastName());
        result.setPhoneNumber(user.getPhoneNumber());
        result.setCountry(user.getCountry());
        result.setPostalCode(user.getPostalCode());
        result.setUsername(user.getUsername());
        return result;
    }

}
