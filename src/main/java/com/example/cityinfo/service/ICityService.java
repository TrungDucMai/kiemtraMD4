package com.example.cityinfo.service;

import com.example.cityinfo.model.City;

import java.util.Optional;

public interface ICityService {
    Iterable<City> findAll();

    Optional<City> findById(Long id);

    void save(City city);

    void remove(Long id);
}
