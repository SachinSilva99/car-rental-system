package com.carentalsystem.api;

import com.carentalsystem.dto.CarDTO;
import com.carentalsystem.service.CarService;
import com.carentalsystem.util.enums.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/crs/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StandardResponse> save(@RequestBody CarDTO carDTO) {
        System.out.println(carDTO);
        String id = carService.create(carDTO);
        return new ResponseEntity<>(
                new StandardResponse(
                        204, id, null
                ),
                HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StandardResponse> get(@PathVariable String id) throws ClassNotFoundException {
        CarDTO carDTO = carService.get(id);
        return new ResponseEntity<>(
                new StandardResponse(
                        200, id, carDTO
                ),
                HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StandardResponse> update(@PathVariable String id, @RequestBody CarDTO carDTO) throws ClassNotFoundException {
        carDTO.setId(id);
        carService.update(carDTO, id);
        return new ResponseEntity<>(new StandardResponse(), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StandardResponse> delete(@PathVariable String id) throws ClassNotFoundException {
        carService.delete(id);
        return new ResponseEntity<>(new StandardResponse(), HttpStatus.NO_CONTENT);
    }
}
