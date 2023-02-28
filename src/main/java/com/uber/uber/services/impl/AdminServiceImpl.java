package com.uber.uber.services.impl;

import com.uber.uber.EntryDtos.AdminEntryDto;
import com.uber.uber.ResponseDtos.CustomerResponseDto;
import com.uber.uber.ResponseDtos.DriverResponseDto;
import com.uber.uber.convertors.CustomerConvertor;
import com.uber.uber.convertors.DriverConvertor;
import com.uber.uber.models.Admin;
import com.uber.uber.models.Customer;
import com.uber.uber.models.Driver;
import com.uber.uber.repository.AdminRepository;
import com.uber.uber.repository.CustomerRepository;
import com.uber.uber.repository.DriverRepository;
import com.uber.uber.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public String adminRegister(AdminEntryDto adminEntryDto) {
        // create Admin Entity

        Admin admin = Admin.builder()
                .username(adminEntryDto.getUsername())
                .password(adminEntryDto.getPassword())
                .build();

        //Save the admin in the database
        adminRepository1.save(admin);
        return "Amin added successfully";
    }

    @Override
    public String updatePassword(Integer adminId, String password) throws Exception {
        //Update the password of admin
        Admin admin = adminRepository1.findById(adminId).get();
        if (admin== null){
            throw new Exception("Enter wrong adminId");
        }

        admin.setPassword(password);
        adminRepository1.save(admin);
        return "Admin password updated successfully";
    }

    @Override
    public String deleteAdmin(int adminId) throws Exception {
        // Delete admin
        Admin admin = adminRepository1.findById(adminId).get();

        if (admin==null){

            throw new Exception("Enter wrong adminId");
        }

        adminRepository1.delete(admin);

        return "Admin deleted successfully";
    }

    @Override
    public List<DriverResponseDto> getListOfDrivers() {
        // list of all drivers
        List<Driver> driverList = driverRepository1.findAll();
        List<DriverResponseDto> driverResponseDtoList = new ArrayList<>();

        for (Driver driver : driverList){
            DriverResponseDto driverResponseDto = DriverConvertor.ConvertorDriverEntityToDto(driver);

            driverResponseDtoList.add(driverResponseDto);
        }
        return driverResponseDtoList;
    }

    @Override
    public List<CustomerResponseDto> getListOfCustomers() {
        List<Customer> customerList = customerRepository1.findAll();
        List<CustomerResponseDto> customerResponseDtoList = new ArrayList<>();

        for (Customer customer : customerList){
            CustomerResponseDto customerResponseDto = CustomerConvertor.convertCustomerEntityToDto(customer);

            customerResponseDtoList.add(customerResponseDto);
        }
        return customerResponseDtoList;
    }

}
