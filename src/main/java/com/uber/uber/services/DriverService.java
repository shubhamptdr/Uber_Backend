package com.uber.uber.services;

import com.uber.uber.EntryDtos.DriverEntityDto;

public interface DriverService {
		public String register(DriverEntityDto driverEntityDto);
		public String removeDriver(int driverId);
		public String updateStatus(int driverId);
}
