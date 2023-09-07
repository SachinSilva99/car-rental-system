package com.carentalsystem.api;

import com.carentalsystem.dto.CarDTO;
import com.carentalsystem.service.CarService;
import com.carentalsystem.util.enums.StandardResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/crs/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<StandardResponse> save(

            @RequestParam("carDTO") String carDTOJson,
            @RequestPart("img1") byte[] img1,
            @RequestPart("img2") byte[] img2,
            @RequestPart("img3") byte[] img3,
            @RequestPart("img4") byte[] img4
    ) throws IOException {

        CarDTO carDTO = new ObjectMapper().readValue(carDTOJson, CarDTO.class);

        carDTO.setImg1(img1);
        carDTO.setImg2(img2);
        carDTO.setImg3(img3);
        carDTO.setImg4(img4);
        String id = carService.create(carDTO);

        return new ResponseEntity<>(
                new StandardResponse(
                        HttpStatus.CREATED.value(),
                        id,
                        null
                ),
                HttpStatus.CREATED
        );
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StandardResponse> get(@PathVariable String id) throws ClassNotFoundException {
        CarDTO carDTO = carService.get(id);
        return new ResponseEntity<>(
                new StandardResponse(
                        200, id, carDTO
                ),
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<StandardResponse> update(
            @PathVariable String id,
            @RequestBody CarDTO carDTO) throws ClassNotFoundException {
        carDTO.setId(id);
        carService.update(carDTO, id);
        return new ResponseEntity<>(new StandardResponse(), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StandardResponse> delete(@PathVariable String id) throws ClassNotFoundException {
        carService.delete(id);
        return new ResponseEntity<>(new StandardResponse(), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{pageNumber}/{noOfCars}")
    public ResponseEntity<StandardResponse> findAll(@PathVariable int noOfCars, @PathVariable int pageNumber){
        Page<CarDTO> carDTOPage = carService.findAll(pageNumber, noOfCars);
        return new ResponseEntity<>(new StandardResponse(200,"ok",carDTOPage), HttpStatus.OK);
    }
}
