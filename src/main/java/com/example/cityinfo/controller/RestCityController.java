package com.example.cityinfo.controller;

import com.example.cityinfo.model.City;
import com.example.cityinfo.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.OptionalInt;

@RestController
@RequestMapping("/rest")
public class RestCityController {
    @Autowired
    ICityService cityService;

    @GetMapping("/cities")
    public ResponseEntity<City> showCityList() {
        Iterable<City> cityList = cityService.findAll();
        return new ResponseEntity(cityList, HttpStatus.OK);
    }
    @GetMapping("/city/{id}")
    public ResponseEntity<City> showCityDetail(@PathVariable  Long id){
        City city = cityService.findById(id).get();
        return new ResponseEntity<>(city,HttpStatus.OK);
    }

    @PostMapping("/cities")
    public ResponseEntity<City> createCity(@RequestBody City city){
        cityService.save(city);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @PutMapping("/cities/{id}")
    public ResponseEntity<City> editCity(@PathVariable Long id, @RequestBody City city){
        Optional<City> city1 = cityService.findById(id);
        if(!city1.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        city.setId(id);
        cityService.save(city);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/cities/{id}")
    public ResponseEntity<City> deleteCity(@PathVariable Long id){
        Optional<City> city = cityService.findById(id);
        if(!city.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        cityService.remove(city.get().getId());
        return new ResponseEntity<>( HttpStatus.OK);
    }
}
