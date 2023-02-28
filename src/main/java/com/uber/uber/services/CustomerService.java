package com.uber.uber.services;



import com.uber.uber.EntryDtos.CustomerEntryDto;
import com.uber.uber.models.Customer;
import com.uber.uber.models.TripBooking;


public interface CustomerService {

	public String register(CustomerEntryDto customerEntryDto);

	public String deleteCustomer(Integer customerId) throws Exception;
	
	public TripBooking bookTrip(int customerId, String fromLocation, String toLocation, int distanceInKm) throws Exception;
	
	public void cancelTrip(Integer tripId);

	public void completeTrip(Integer tripId);
	
}
