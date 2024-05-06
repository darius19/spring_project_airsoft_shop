package com.airsoftShop.airsoftShop.dto;


import com.airsoftShop.airsoftShop.model.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserProfileDto {
    private String id;
    private String firstName;
    private String lastName;
    private String username;
    private String phoneNumber;
    private String address;
    private String city;
    private String country;
    private String postalCode;
    private String dob;
}
