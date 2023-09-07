package com.carentalsystem.service.impl;

import com.carentalsystem.dto.DriverDTO;
import com.carentalsystem.dto.RentalRequestDTO;
import com.carentalsystem.entity.*;
import com.carentalsystem.repo.CarRepo;
import com.carentalsystem.repo.CustomerRepo;
import com.carentalsystem.repo.RentalRequestRepo;
import com.carentalsystem.repo.ScheduleRepo;
import com.carentalsystem.service.PlaceRentalRequest;
import com.carentalsystem.util.IdGenerator;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlaceRentalRequestImpl implements PlaceRentalRequest {

    @Autowired
    private RentalRequestRepo rentalRepo;

    @Autowired
    private IdGenerator idGenerator;

    @Autowired
    private ScheduleRepo scheduleRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private CarRepo carRepo;

    @Autowired
    private ModelMapper mapper;

    @Transactional
    @Override
    public String placeRentalRequest(RentalRequestDTO dto, DriverDTO driverDTO) throws ClassNotFoundException {
        String id = idGenerator.generateRandomID(10);
        while (rentalRepo.findById(id).isPresent()) {
            id = idGenerator.generateRandomID(10);
        }

        dto.setRequestId(id);
        RentalRequest rentalRequest = mapper.map(dto, RentalRequest.class);
        rentalRepo.save(rentalRequest);

        String carId = dto.getCarId();
        if (carRepo.findById(carId).isEmpty()) {
            throw new ClassNotFoundException(carId + " is not found");
        }
        Car car = carRepo.findById(carId).get();
        car.getRentalRequestList().add(rentalRequest);
        rentalRequest.setCar(car);


        String customerId = dto.getCustomerId();
        if (customerRepo.findById(customerId).isEmpty()) {
            throw new ClassNotFoundException(customerId + " is not found");
        }
        Customer customer = customerRepo.findById(customerId).get();
        customer.getRentalRequestList().add(rentalRequest);
        rentalRequest.setCustomer(customer);

        Schedule schedule = new Schedule();
        schedule.setDriver(mapper.map(driverDTO, Driver.class));
        schedule.setRentalRequest(rentalRequest);
        scheduleRepo.save(schedule);
        return rentalRequest.getRequestId();
    }
}
