package com.airsoftShop.airsoftShop.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    ROLE_ADMIN("Administrator", "ADMIN"),
    ROLE_USER("User","USER");

    private final String serviceName;
    private final String securityName;



}
