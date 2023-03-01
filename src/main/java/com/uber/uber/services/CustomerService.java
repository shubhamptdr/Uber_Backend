package com.uber.uber.services;



import com.uber.uber.EntryDtos.BookTripEntryDto;
import com.uber.uber.EntryDtos.CustomerEntryDto;
import com.uber.uber.ResponseDtos.TripBookingResponseDto;
import com.uber.uber.models.Customer;
import com.uber.uber.models.TripBooking;

import java.util.List;


public interface CustomerService {

	public String register(CustomerEntryDto customerEntryDto);

	public String deleteCustomer(Integer customerId) throws Exception;
	
	public TripBooking bookTrip(BookTripEntryDto bookTripEntryDto) throws Exception;
	
	public void cancelTrip(Integer tripId);

	public void completeTrip(Integer tripId);

	List<TripBookingResponseDto> getTripBookingListByCustomerId(int customerId);
}
