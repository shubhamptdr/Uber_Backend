package com.uber.uber.services.impl;

import com.uber.uber.enums.TripStatus;
import com.uber.uber.models.Cab;
import com.uber.uber.models.Driver;
import com.uber.uber.models.TripBooking;
import com.uber.uber.repository.CabRepository;
import com.uber.uber.repository.DriverRepository;
import com.uber.uber.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    DriverRepository driverRepository3;

    @Autowired
    CabRepository cabRepository3;

    @Override
    public void register(String mobile, String password){
        //Save a driver in the database having given details and a cab with ratePerKm as 10 and availability as True by default.
        Cab cab = Cab.builder()
                .perKmRate(10)
                .available(true)
                .build();

        Driver driver = Driver.builder()
                .mobile(mobile)
                .password(password)
                .cab(cab)
                .build();

        // by cascading
        driverRepository3.save(driver);

    }

    @Override
    public void removeDriver(int driverId){
        // Delete driver without using deleteById function
        Driver driver = driverRepository3.findById(driverId).get();
        Cab cab = driver.getCab();
        cabRepository3.delete(cab);

        List<TripBooking> tripBookingList = driver.getTripBookingList();

        for(TripBooking tripBooking : tripBookingList){
            if(tripBooking.getStatus() == TripStatus.CONFIRMED){
                tripBooking.setStatus(TripStatus.CANCELED);
            }
        }
        driverRepository3.delete(driver);

    }

    @Override
    public void updateStatus(int driverId){
        //Set the status of respective car to unavailable
        Driver driver = driverRepository3.findById(driverId).get();
        Cab cab = cabRepository3.findById(driver.getCab().getId()).get();
        cab.setAvailable(false);
        driver.setCab(cab);

        driverRepository3.save(driver);

    }
}
