package com.carentalsystem.api;


import com.carentalsystem.dto.DriverDTO;
import com.carentalsystem.service.DriverService;
import com.carentalsystem.service.exception.DuplicationException;
import com.carentalsystem.util.enums.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/crs/drivers")
public class DriverController {


    @Autowired
    private DriverService driverService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StandardResponse> save(@RequestBody DriverDTO driverDTO) throws DuplicationException {
        String id = driverService.create(driverDTO);
        return new ResponseEntity<>(new StandardResponse(204,id,null),HttpStatus.CREATED);
    }
}
