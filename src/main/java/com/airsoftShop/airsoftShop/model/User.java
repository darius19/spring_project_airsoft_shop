package com.airsoftShop.airsoftShop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String city;
    private String country;
    private String postalCode;
    @Enumerated(EnumType.STRING )
    private Role role;
    private String username;
    private String password;
    private String email;
    private LocalDateTime dob;


}
