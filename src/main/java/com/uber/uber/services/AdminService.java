package com.uber.uber.services;

import com.uber.uber.models.Admin;
import com.uber.uber.models.Customer;
import com.uber.uber.models.Driver;

import java.util.List;

public interface AdminService {

	public void adminRegister(Admin admin);

	public Admin updatePassword(Integer adminId, String password);

	public void deleteAdmin(int adminId);

	public List<Driver> getListOfDrivers();
	
	public List<Customer> getListOfCustomers();
}
