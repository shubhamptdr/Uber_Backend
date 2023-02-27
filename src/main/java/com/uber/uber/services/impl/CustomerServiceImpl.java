package com.uber.uber.services.impl;

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
    public void register(Customer customer) {
        //Save the customer in database
        customerRepository2.save(customer);
    }

    @Override
    public void deleteCustomer(Integer customerId) {
        // Delete customer without using deleteById function
        Customer customer = customerRepository2.findById(customerId).get();
        List<TripBooking> tripBookingList = customer.getTripBookingList();

        for(TripBooking tripBooking : tripBookingList){
            if(tripBooking.getStatus() == TripStatus.CONFIRMED){
                tripBooking.setStatus(TripStatus.CANCELED);
            }
        }
        customerRepository2.delete(customer);

    }

    @Override
    public TripBooking bookTrip(int customerId, String fromLocation, String toLocation, int distanceInKm) throws Exception{
        //Book the driver with lowest driverId who is free (cab available variable is Boolean.TRUE). If no driver is available, throw "No cab available!" exception
        //Avoid using SQL query
        TripBooking tripBooking = new TripBooking();
        Driver availableDriver = null;
        List<Driver> driverList = driverRepository2.findAll();

        for(Driver driver : driverList){
            if(driver.getCab().isAvailable() == true){
                if((availableDriver == null) || (availableDriver.getDriverId() > driver.getDriverId())){
                    availableDriver = driver;
                }
            }
        }

        if(availableDriver == null){
            throw new Exception("No cab available!");
        }

        Customer customer = customerRepository2.findById(customerId).get();
        tripBooking.setCustomer(customer);
        tripBooking.setDriver(availableDriver);
        tripBooking.setStatus(TripStatus.CONFIRMED);
        tripBooking.setFromLocation(fromLocation);
        tripBooking.setToLocation(toLocation);
        availableDriver.getCab().setAvailable(false);
        tripBooking.setDistanceInKm(distanceInKm);

        int rate = availableDriver.getCab().getPerKmRate();
        tripBooking.setBill(distanceInKm * rate);

        customer.getTripBookingList().add(tripBooking);
        customerRepository2.save(customer);

        availableDriver.getTripBookingList().add(tripBooking);
        driverRepository2.save(availableDriver);

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
}
