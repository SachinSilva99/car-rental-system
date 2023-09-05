package com.carentalsystem.service.impl;

import com.carentalsystem.dto.CarDTO;
import com.carentalsystem.entity.Car;
import com.carentalsystem.repo.CarRepo;
import com.carentalsystem.service.CarService;
import com.carentalsystem.service.exception.DuplicationException;
import com.carentalsystem.service.exception.InUseException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepo carRepo;

    @Autowired
    private  ModelMapper mapper;


    @Override
    @Transactional
    public String create(CarDTO carDTO) throws DuplicationException {
        String id = generateRandomID(6);
        while (carRepo.findById(id).isPresent()) {
            id = generateRandomID(6);
        }
        Car car = mapper.map(carDTO, Car.class);
        car.setId(id);
        return carRepo.save(car).getId();
    }

    @Override
    public CarDTO get(String id) throws ClassNotFoundException {
        return null;
    }

    @Override
    public void update(CarDTO carDTO, String id) throws ClassNotFoundException {

    }

    @Override
    public void delete(String id) throws InUseException {

    }

    public String generateRandomID(int length) {
        final String VALID_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder idBuilder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(VALID_CHARACTERS.length());
            char randomChar = VALID_CHARACTERS.charAt(randomIndex);
            idBuilder.append(randomChar);
        }
        return idBuilder.toString();
    }
}
