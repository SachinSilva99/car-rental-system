package com.carentalsystem.service.impl;

import com.carentalsystem.dto.CarDTO;
import com.carentalsystem.entity.Car;
import com.carentalsystem.repo.CarRepo;
import com.carentalsystem.service.CarService;
import com.carentalsystem.service.exception.InUseException;
import com.carentalsystem.util.IdGenerator;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepo carRepo;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private IdGenerator idGenerator;

    @Override
    @Transactional
    public String create(CarDTO carDTO)  {
        String id = idGenerator.generateRandomID(10);
        while (carRepo.findById(id).isPresent()) {
            id = idGenerator.generateRandomID(10);
        }
        Car car = mapper.map(carDTO, Car.class);
        car.setId(id);
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
    @Transactional
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
    @Override
    public Page<CarDTO> findAll(int page, int noOfCars){
        Page<Car> carPage = carRepo.findAll(PageRequest.of(page, noOfCars));
        return carPage.map(car -> mapper.map(car, CarDTO.class));
    }
}
