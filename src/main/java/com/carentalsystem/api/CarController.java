package com.carentalsystem.api;

import com.carentalsystem.dto.CarDTO;
import com.carentalsystem.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/crs/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CarDTO> save(@RequestBody CarDTO carDTO) {
        System.out.println(carDTO);
        carService.create(carDTO);
        return null;
    }
}
