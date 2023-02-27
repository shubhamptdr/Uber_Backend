package com.uber.uber.models;

import com.uber.uber.enums.TripStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "trip_booking")
@Data
@NoArgsConstructor
@AllArgsConstructor
public
class TripBooking{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tripBookingId;

    private String fromLocation;

    private String toLocation;

    private int distanceInKm;

    @Enumerated(value = EnumType.STRING)
    private TripStatus status;

    private int bill;

    // child wrt to customer
    @ManyToOne
    @JoinColumn
    private Customer customer;

    // child wrt to customer
    @ManyToOne
    @JoinColumn
    private Driver driver;

}