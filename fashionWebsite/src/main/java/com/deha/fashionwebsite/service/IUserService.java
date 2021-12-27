package com.deha.fashionwebsite.service;

import com.deha.fashionwebsite.dto.UserRegistrationDto;
import com.deha.fashionwebsite.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService {
    User save(UserRegistrationDto registrationDto);
    User findByUsername(String username);
}