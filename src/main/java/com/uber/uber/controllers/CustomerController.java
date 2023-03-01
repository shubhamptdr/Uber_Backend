package com.uber.uber.controllers;

import com.uber.uber.EntryDtos.BookTripEntryDto;
import com.uber.uber.EntryDtos.CustomerEntryDto;
import com.uber.uber.ResponseDtos.TripBookingResponseDto;
import com.uber.uber.models.Customer;
import com.uber.uber.models.TripBooking;
import com.uber.uber.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	CustomerService customerService;
	@PostMapping("/register")
	public ResponseEntity<String> registerCustomer(@RequestBody CustomerEntryDto customerEntryDto){

		String response = customerService.register(customerEntryDto);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteCustomer(@RequestParam Integer customerId){

		try {
			String response = customerService.deleteCustomer(customerId);
			return new ResponseEntity<>(response, HttpStatus.OK);

		}catch (Exception e){
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/bookTrip")
	public ResponseEntity<String> bookTrip(@RequestBody BookTripEntryDto bookTripEntryDto) throws Exception {
		try {

		TripBooking bookedTrip = customerService.bookTrip(bookTripEntryDto);
		return new ResponseEntity<>("tripId: "+bookedTrip.getTripBookingId(), HttpStatus.CREATED);
		}catch (Exception e){
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CREATED);
		}
	}

	@DeleteMapping("/complete")
	public void completeTrip(@RequestParam Integer tripId){
		customerService.completeTrip(tripId);
	}

	@DeleteMapping("/cancelTrip")
	public void cancelTrip(@RequestParam Integer tripId){
		customerService.cancelTrip(tripId);
	}

	@GetMapping("/get-trip-bookings")
	public ResponseEntity<List<TripBookingResponseDto>> getTripBookingListByCustomerId(@RequestParam int customerId){

		List<TripBookingResponseDto> response = customerService.getTripBookingListByCustomerId(customerId);
		return new ResponseEntity<>(response,HttpStatus.OK);

	}
}
