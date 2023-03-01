package com.uber.uber.services.impl;

import com.uber.uber.EntryDtos.DriverEntityDto;
import com.uber.uber.ResponseDtos.TripBookingResponseDto;
import com.uber.uber.convertors.DriverConvertor;
import com.uber.uber.enums.TripStatus;
import com.uber.uber.models.Cab;
import com.uber.uber.models.Driver;
import com.uber.uber.models.TripBooking;
import com.uber.uber.repository.CabRepository;
import com.uber.uber.repository.DriverRepository;
import com.uber.uber.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    DriverRepository driverRepository3;

    @Autowired
    CabRepository cabRepository3;

    @Override
    public String register(DriverEntityDto driverEntityDto){
        //Save a driver in the database having given details and a cab with ratePerKm as 10 and availability as True by default.
        // create parent entity
        Driver driver = Driver.builder()
                .mobile(driverEntityDto.getMobile())
                .password(driverEntityDto.getPassword())
                .build();

        // create child entity
        Cab cab = Cab.builder()
                .perKmRate(10)
                .available(true)
                .driver(driver)
                .build();

        // save driver attribute
        driver.setCab(cab);

        // save parent by cascading child automatically save
        driverRepository3.save(driver);
        return "Driver register successfully";
    }

    @Override
    public String removeDriver(int driverId){
        // Delete driver without using deleteById function
        // fetch driver
        Driver driver = driverRepository3.findById(driverId).get();

        // set tripBooking status CANCELED
        List<TripBooking> tripBookingList = driver.getTripBookingList();
        for(TripBooking tripBooking : tripBookingList){
            if(tripBooking.getStatus() == TripStatus.CONFIRMED){
                tripBooking.setStatus(TripStatus.CANCELED);
            }
        }

        driverRepository3.delete(driver);
        return "Driver removed successfully";
    }

    @Override
    public String updateStatus(int driverId){
        //Set the status of respective car to unavailable
        Driver driver = driverRepository3.findById(driverId).get();
        Cab cab = cabRepository3.findById(driver.getCab().getId()).get();
        cab.setAvailable(false);
        driver.setCab(cab);

        driverRepository3.save(driver);
        return "Driver status updated  to engaged successfully";
    }

    @Override
    public List<TripBookingResponseDto> getTripBookingListByDriverId(int driverId) {
        Driver driver = driverRepository3.findById(driverId).get();

        List<TripBooking> tripBookingList = driver.getTripBookingList();
        List<TripBookingResponseDto> tripBookingResponseDtoList = new ArrayList<>();

        for ( TripBooking tripBooking : tripBookingList){
            TripBookingResponseDto tripBookingResponseDto = DriverConvertor.ConvertorTripBookingEntityToDto(tripBooking);
            tripBookingResponseDtoList.add(tripBookingResponseDto);
        }

        return tripBookingResponseDtoList;
    }
}
