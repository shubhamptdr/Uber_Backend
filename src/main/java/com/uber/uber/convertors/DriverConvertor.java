package com.uber.uber.convertors;

import com.uber.uber.ResponseDtos.DriverResponseDto;
import com.uber.uber.models.Driver;

public class DriverConvertor {
    public static DriverResponseDto ConvertorDriverEntityToDto(Driver driver){
        return DriverResponseDto.builder()
                .driverId(driver.getDriverId())
                .available(driver.getCab().isAvailable())
                .mobile(driver.getMobile())
                .build();
    }
}
