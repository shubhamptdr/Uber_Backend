package com.uber.uber.services.impl;

import com.uber.uber.EntryDtos.BookTripEntryDto;
import com.uber.uber.EntryDtos.CustomerEntryDto;
import com.uber.uber.ResponseDtos.TripBookingResponseDto;
import com.uber.uber.convertors.DriverConvertor;
import com.uber.uber.enums.TripStatus;
import com.uber.uber.models.Customer;
import com.uber.uber.models.Driver;
import com.uber.uber.models.TripBooking;
import com.uber.uber.repository.CustomerRepository;
import com.uber.uber.repository.DriverRepository;
import com.uber.uber.repository.TripBookingRepository;
import com.uber.uber.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository2;

    @Autowired
    DriverRepository driverRepository2;

    @Autowired
    TripBookingRepository tripBookingRepository2;

    @Override
    public String register(CustomerEntryDto customerEntryDto) {
        // crete entity
        Customer customer = Customer.builder()
                .name(customerEntryDto.getName())
                .mobile(customerEntryDto.getMobile())
                .password(customerEntryDto.getPassword())
                .build();

        //Save the customer in database
        customerRepository2.save(customer);

        return "Customer added successfully";
    }

    @Override
    public String deleteCustomer(Integer customerId) throws Exception {
        // Delete customer without using deleteById function
        Customer customer = customerRepository2.findById(customerId).get();
        if (customer == null){
            throw new Exception("Enter wrong customerId");
        }
        List<TripBooking> tripBookingList = customer.getTripBookingList();

        for(TripBooking tripBooking : tripBookingList){
            if(tripBooking.getStatus() == TripStatus.CONFIRMED){
                tripBooking.setStatus(TripStatus.CANCELED);
            }
        }
        customerRepository2.delete(customer);

        return "Customer added successfully";
    }

    @Override
    public TripBooking bookTrip(BookTripEntryDto bookTripEntryDto) throws Exception{
        //Book the driver with lowest driverId who is free (cab available variable is Boolean.TRUE). If no driver is available, throw "No cab available!" exception
        //Avoid using SQL query
        Driver availableDriver = null;
        List<Driver> driverList = driverRepository2.findAll();

        for(Driver driver : driverList){
            if(driver.getCab().isAvailable()){
                if((availableDriver == null) || (availableDriver.getDriverId() > driver.getDriverId())){
                    availableDriver = driver;
                }
            }
        }

        if(availableDriver == null){
            throw new Exception("No cab available!");
        }
        // fetch Customer Entity
        Customer customer = customerRepository2.findById(bookTripEntryDto.getCustomerId()).get();

        if(customer == null){
            throw new Exception("Enter wrong customerID!");
        }

        // create TripBook entity and setting its attribute

        TripBooking tripBooking = TripBooking.builder()
                .driver(availableDriver)
                .customer(customer)
                .fromLocation(bookTripEntryDto.getFromLocation())
                .toLocation(bookTripEntryDto.getToLocation())
                .distanceInKm(bookTripEntryDto.getDistanceInKm())
                .status(TripStatus.CONFIRMED)
                .bill(availableDriver.getCab().getPerKmRate() * bookTripEntryDto.getDistanceInKm())
                .build();

        availableDriver.getCab().setAvailable(false);

//        customer.getTripBookingList().add(tripBooking);
//        availableDriver.getTripBookingList().add(tripBooking);
//        customerRepository2.save(customer);
////
//        driverRepository2.save(availableDriver);

            tripBookingRepository2.save(tripBooking);
        return tripBooking;
    }

    @Override
    public void cancelTrip(Integer tripId){
        //Cancel the trip having given trip Id and update TripBooking attributes accordingly
        TripBooking tripBooking = tripBookingRepository2.findById(tripId).get();
        tripBooking.setStatus(TripStatus.CANCELED);
        tripBooking.setBill(0);
        tripBooking.getDriver().getCab().setAvailable(true);
        tripBookingRepository2.save(tripBooking);

    }

    @Override
    public void completeTrip(Integer tripId){
        //Complete the trip having given trip Id and update TripBooking attributes accordingly
        TripBooking tripBooking = tripBookingRepository2.findById(tripId).get();
        tripBooking.setStatus(TripStatus.COMPLETED);
        Driver driver = tripBooking.getDriver();
        tripBooking.setBill(tripBooking.getDistanceInKm() * driver.getCab().getPerKmRate());
        tripBooking.getDriver().getCab().setAvailable(true);
        tripBookingRepository2.save(tripBooking);
    }

    @Override
    public List<TripBookingResponseDto> getTripBookingListByCustomerId(int customerId) {
        Customer customer = customerRepository2.findById(customerId).get();

        List<TripBooking> tripBookingList = customer.getTripBookingList();
        List<TripBookingResponseDto> tripBookingResponseDtoList = new ArrayList<>();

        for ( TripBooking tripBooking : tripBookingList){
            TripBookingResponseDto tripBookingResponseDto = DriverConvertor.ConvertorTripBookingEntityToDto(tripBooking);
            tripBookingResponseDtoList.add(tripBookingResponseDto);
        }

        return tripBookingResponseDtoList;
    }
}
