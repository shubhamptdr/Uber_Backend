package com.uber.uber.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "cab")
@Data
@NoArgsConstructor
@AllArgsConstructor
public
class Cab{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int perKmRate;

    private boolean available;

    // parent wrt to driver
    @OneToOne(mappedBy = "cab",cascade = CascadeType.ALL)
    private Driver driver;

}