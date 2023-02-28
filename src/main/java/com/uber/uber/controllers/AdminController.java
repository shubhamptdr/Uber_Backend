package com.uber.uber.controllers;


import com.uber.uber.EntryDtos.AdminEntryDto;
import com.uber.uber.ResponseDtos.CustomerResponseDto;
import com.uber.uber.ResponseDtos.DriverResponseDto;

import com.uber.uber.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	AdminService adminService;

	@PostMapping("/register")
	public ResponseEntity<String> registerAdmin(@RequestBody AdminEntryDto adminEntryDto){
		String response = adminService.adminRegister(adminEntryDto);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PutMapping("/update")
	public ResponseEntity<String> updateAdminPassword(@RequestParam Integer adminId, @RequestParam String password) throws Exception{
		try {

			String response = adminService.updatePassword(adminId,password);
			return new ResponseEntity<>(response, HttpStatus.OK);

		}catch (Exception e){
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteAdmin(@RequestParam Integer adminId){
		try {

			String response = adminService.deleteAdmin(adminId);
			return new ResponseEntity<>(response, HttpStatus.OK);

		}catch (Exception e){
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/listOfCustomers")
	public List<CustomerResponseDto> listOfCustomers() {
		List<CustomerResponseDto> listOfCustomers = adminService.getListOfCustomers();
		return listOfCustomers;
	}

	@GetMapping("/listOfDrivers")
	public List<DriverResponseDto> listOfDrivers() {
		List<DriverResponseDto> listOfDrivers = adminService.getListOfDrivers();
		return listOfDrivers;
	}
}
