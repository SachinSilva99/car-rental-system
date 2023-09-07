package com.carentalsystem.service.impl;

import com.carentalsystem.dto.DriverDTO;
import com.carentalsystem.entity.Driver;
import com.carentalsystem.repo.DriverRepo;
import com.carentalsystem.service.DriverService;
import com.carentalsystem.service.exception.DuplicationException;
import com.carentalsystem.service.exception.InUseException;
import com.carentalsystem.util.IdGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverRepo driverRepo;

    @Autowired
    private IdGenerator idGenerator;

    @Autowired
    private ModelMapper mapper;

    @Override
    public String create(DriverDTO driverDTO) {
        String id = idGenerator.generateRandomID(10);
        id += driverDTO.getContactNo();
        while (driverRepo.findById(id).isPresent()) {
            id = idGenerator.generateRandomID(10);
            id += driverDTO.getContactNo();
        }
        driverDTO.setId(id);
        try {
            return driverRepo.save(mapper.map(driverDTO, Driver.class)).getId();
        } catch (DataIntegrityViolationException e) {
            throw new DuplicationException("id :" + id + " contact number Already exists");
        }

    }

    @Override
    public DriverDTO get(String id) throws ClassNotFoundException {
        Optional<Driver> byId = driverRepo.findById(id);
        if (byId.isEmpty()) {
            throw new ClassNotFoundException(id + " not found");
        }
        return mapper.map(byId.get(), DriverDTO.class);
    }

    @Override
    public void update(DriverDTO driverDTO, String id) throws ClassNotFoundException {
        Optional<Driver> byId = driverRepo.findById(id);
        if (byId.isEmpty()) {
            throw new ClassNotFoundException(id + " not found");
        }
        Driver driver = byId.get();
        driver.setName(driverDTO.getName());
        driver.setContactNo(driverDTO.getContactNo());
        driverRepo.save(driver);
        try {
            driverRepo.save(driver);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicationException("id :" + id + " contact no Already exists");
        }
    }

    @Override
    public void delete(String id) throws InUseException, ClassNotFoundException {
        Optional<Driver> byId = driverRepo.findById(id);
        if (byId.isEmpty()) {
            throw new ClassNotFoundException(id + " not found");
        }
        try {
            driverRepo.delete(byId.get());
        }catch (Exception e){
            throw new InUseException(id+ " in use");
        }
    }

    @Override
    public Page<DriverDTO> findAll(int page, int noOfCars) {
        Page<Driver> carPage = driverRepo.findAll(PageRequest.of(page, noOfCars));
        return carPage.map(driver -> mapper.map(driver, DriverDTO.class));
    }
}
