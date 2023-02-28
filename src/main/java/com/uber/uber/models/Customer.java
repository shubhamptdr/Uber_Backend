package com.uber.uber.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public
class Customer{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;

    private String name;
    private String mobile;

    private String password;

    // parent wrt to tripBooking
    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<TripBooking> tripBookingList = new ArrayList<>();

}