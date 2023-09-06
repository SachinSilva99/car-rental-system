package com.carentalsystem.service.impl;

import com.carentalsystem.dto.CarDTO;
import com.carentalsystem.entity.Car;
import com.carentalsystem.repo.CarRepo;
import com.carentalsystem.service.CarService;
import com.carentalsystem.service.exception.InUseException;
import com.carentalsystem.util.GenerateId;
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
    private ModelMapper mapper;

    @Autowired
    private GenerateId generateId;

    @Override
    @Transactional
    public String create(CarDTO carDTO)  {
        String id = generateId.generateRandomID(10);
        while (carRepo.findById(id).isPresent()) {
            id = generateId.generateRandomID(10);
        }
        Car car = mapper.map(carDTO, Car.class);
        car.setId(id);

        System.out.println(car);
        return carRepo.save(car).getId();
    }

    @Override
    public CarDTO get(String id) throws ClassNotFoundException {
        Optional<Car> byId = carRepo.findById(id);
        if (byId.isEmpty()) {
            throw new ClassNotFoundException(id + " not found");
        }
        return mapper.map(byId.get(), CarDTO.class);
    }

    @Override
    public void update(CarDTO carDTO, String id) throws ClassNotFoundException {
        Optional<Car> byId = carRepo.findById(id);
        if (byId.isEmpty()) {
            throw new ClassNotFoundException(id + " not found");
        }
        Car car = byId.get();
        car.setCarType(carDTO.getCarType());
        car.setDescription(carDTO.getDescription());
        car.setDailyRate(carDTO.getDailyRate());
        car.setMonthlyRate(carDTO.getMonthlyRate());
        car.setFreeKmsPerDay(carDTO.getFreeKmsPerDay());
        car.setFreeKmsPerMonth(carDTO.getFreeKmsPerMonth());
        car.setImg1(carDTO.getImg1());
        car.setImg2(carDTO.getImg2());
        car.setImg3(carDTO.getImg3());
        car.setImg4(carDTO.getImg4());
        carRepo.save(car);
    }

    @Override
    public void delete(String id) throws InUseException, ClassNotFoundException {
        Optional<Car> byId = carRepo.findById(id);
        if (byId.isEmpty()) {
            throw new ClassNotFoundException(id + " not found");
        }
        try {
            carRepo.delete(byId.get());
        } catch (Exception e) {
            throw new InUseException();
        }
    }
}
