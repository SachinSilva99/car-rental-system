package com.carentalsystem.service.impl;

import com.carentalsystem.dto.CustomerDTO;
import com.carentalsystem.entity.Customer;
import com.carentalsystem.repo.CustomerRepo;
import com.carentalsystem.service.CustomerService;
import com.carentalsystem.service.exception.DuplicationException;
import com.carentalsystem.service.exception.InUseException;
import com.carentalsystem.util.GenerateId;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private GenerateId generateId;

    @Autowired
    private ModelMapper mapper;

    @Override
    @Transactional
    public String create(CustomerDTO customerDTO)  {
        String id = generateId.generateRandomID(10);
        id += customerDTO.getUsername();
        while (customerRepo.findById(id).isPresent()) {
            id = generateId.generateRandomID(10);
            id += customerDTO.getUsername();
        }
        customerDTO.setId(id);
        try {
            return customerRepo.save(mapper.map(customerDTO, Customer.class)).getId();
        }catch (DataIntegrityViolationException e){
            throw new DuplicationException("id :"+id+"   Driving license number \n" +
                    "                  nic number \n" +
                    "                  username \n" +
                    "                  phone number\n" +
                    "                  Already exists");
        }
    }

    @Override
    public CustomerDTO get(String id) throws ClassNotFoundException {
        Optional<Customer> byId = customerRepo.findById(id);
        if (byId.isEmpty()) {
            throw new ClassNotFoundException(id + " not found");
        }
        return mapper.map(byId.get(), CustomerDTO.class);
    }

    @Override
    @Transactional
    public void update(CustomerDTO dto, String id) throws ClassNotFoundException {
        Optional<Customer> byId = customerRepo.findById(id);
        if (byId.isEmpty()) {
            throw new ClassNotFoundException(id + " not found");
        }
        Customer customer = byId.get();
        customer.setAddress(dto.getAddress());
        customer.setNic(dto.getNic());
        customer.setEmail(dto.getEmail());
        customer.setDrivingLicenseNumber(dto.getDrivingLicenseNumber());
        customer.setUsername(dto.getUsername());
        customer.setPassword(dto.getPassword());
        customer.setPhoneNumber(dto.getPhoneNumber());
        customerRepo.save(customer);
    }

    @Override
    public void delete(String id) throws InUseException, ClassNotFoundException {
        Optional<Customer> byId = customerRepo.findById(id);
        if (byId.isEmpty()) {
            throw new ClassNotFoundException(id + " not found");
        }
        try {
            customerRepo.delete(byId.get());
        }catch (Exception e){
            throw new InUseException(id+ " in use");
        }
    }
}
