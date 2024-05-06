package com.airsoftShop.airsoftShop.dto;


import com.airsoftShop.airsoftShop.model.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationDto {
    private String username;
    private String password;
    private String email;
    private String roleKey;


}
