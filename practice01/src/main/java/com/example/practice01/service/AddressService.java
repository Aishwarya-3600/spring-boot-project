package com.example.practice01.service;

import com.example.practice01.repository.jpa.AdressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private AdressRepository addressRepository;

}
