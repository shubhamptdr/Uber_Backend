package com.uber.uber.services;

public interface DriverService {
		public String register(String mobile, String password);
		public String removeDriver(int driverId);
		public String updateStatus(int driverId);
}
