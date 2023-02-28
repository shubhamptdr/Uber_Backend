package com.uber.uber.services;

import com.uber.uber.EntryDtos.AdminEntryDto;
import com.uber.uber.ResponseDtos.CustomerResponseDto;
import com.uber.uber.ResponseDtos.DriverResponseDto;
import com.uber.uber.models.Admin;
import com.uber.uber.models.Customer;
import com.uber.uber.models.Driver;

import java.util.List;

public interface AdminService {

	public String adminRegister(AdminEntryDto adminEntryDto);

	public String updatePassword(Integer adminId, String password) throws Exception;

	public String deleteAdmin(int adminId) throws Exception;

	public List<DriverResponseDto> getListOfDrivers();
	
	public List<CustomerResponseDto> getListOfCustomers();
}
