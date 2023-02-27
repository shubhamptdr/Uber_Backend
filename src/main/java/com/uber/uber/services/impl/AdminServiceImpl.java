package com.uber.uber.services.impl;

import com.uber.uber.models.Admin;
import com.uber.uber.models.Customer;
import com.uber.uber.models.Driver;
import com.uber.uber.repository.AdminRepository;
import com.uber.uber.repository.CustomerRepository;
import com.uber.uber.repository.DriverRepository;
import com.uber.uber.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminRepository adminRepository1;

    @Autowired
    DriverRepository driverRepository1;

    @Autowired
    CustomerRepository customerRepository1;

    @Override
    public void adminRegister(Admin admin) {
        //Save the admin in the database
        adminRepository1.save(admin);
    }

    @Override
    public Admin updatePassword(Integer adminId, String password) {
        //Update the password of admin
        Admin admin = adminRepository1.findById(adminId).get();
        admin.setPassword(password);
        adminRepository1.save(admin);
        return admin;
    }

    @Override
    public void deleteAdmin(int adminId){
        // Delete admin
        Admin admin = adminRepository1.findById(adminId).get();
        adminRepository1.delete(admin);
    }

    @Override
    public List<Driver> getListOfDrivers() {
        // list of all drivers
        return driverRepository1.findAll();
    }

    @Override
    public List<Customer> getListOfCustomers() {
        //list of all customers
        return customerRepository1.findAll();
    }

}
