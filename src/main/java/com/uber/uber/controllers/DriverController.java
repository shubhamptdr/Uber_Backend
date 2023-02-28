package com.uber.uber.controllers;

import com.uber.uber.EntryDtos.DriverEntityDto;
import com.uber.uber.services.impl.DriverServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/driver")
public class DriverController {

	@Autowired
	DriverServiceImpl driverServiceImpl;

	@PostMapping(value = "/register")
	public ResponseEntity<String> registerDriver(@RequestBody DriverEntityDto driverEntityDto){
		String response = driverServiceImpl.register(driverEntityDto);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}

	@DeleteMapping(value = "/delete")
	public ResponseEntity<String> deleteDriver(@RequestParam Integer driverId){

		String response = driverServiceImpl.removeDriver(driverId);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}

	@PutMapping("/status")
	public ResponseEntity<String> updateStatus(@RequestParam Integer driverId){

		String response = driverServiceImpl.updateStatus(driverId);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
}
