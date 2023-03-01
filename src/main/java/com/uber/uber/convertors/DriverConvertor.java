package com.uber.uber.convertors;

import com.uber.uber.ResponseDtos.DriverResponseDto;
import com.uber.uber.ResponseDtos.TripBookingResponseDto;
import com.uber.uber.models.Driver;
import com.uber.uber.models.TripBooking;

public class DriverConvertor {
    public static DriverResponseDto ConvertorDriverEntityToDto(Driver driver){
        return DriverResponseDto.builder()
                .driverId(driver.getDriverId())
                .available(driver.getCab().isAvailable())
                .mobile(driver.getMobile())
                .build();
    }

    public static TripBookingResponseDto ConvertorTripBookingEntityToDto(TripBooking tripBooking){
        return TripBookingResponseDto.builder()
                .tripBookingId(tripBooking.getTripBookingId())
                .fromLocation(tripBooking.getFromLocation())
                .toLocation(tripBooking.getToLocation())
                .distanceInKm(tripBooking.getDistanceInKm())
                .bill(tripBooking.getBill())
                .status(tripBooking.getStatus())
                .customerId(tripBooking.getCustomer().getCustomerId())
                .build();
    }
}
