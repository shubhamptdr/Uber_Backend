package com.uber.uber.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "driver")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public
class Driver{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int driverId;

    private String mobile;

    private String password;

    // parent wrt to cab
    @OneToOne(mappedBy = "driver",cascade = CascadeType.ALL)
    private Cab cab;

    // parent wrt to tripBooking
    @OneToMany(mappedBy = "driver",cascade = CascadeType.ALL)
    private List<TripBooking> tripBookingList = new ArrayList<>();

}