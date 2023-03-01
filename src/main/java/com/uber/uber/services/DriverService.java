package com.uber.uber.services;

import com.uber.uber.EntryDtos.DriverEntityDto;
import com.uber.uber.ResponseDtos.TripBookingResponseDto;

import java.util.List;

public interface DriverService {
		public String register(DriverEntityDto driverEntityDto);
		public String removeDriver(int driverId);
		public String updateStatus(int driverId);
		public List<TripBookingResponseDto> getTripBookingListByDriverId(int driverId);
}
