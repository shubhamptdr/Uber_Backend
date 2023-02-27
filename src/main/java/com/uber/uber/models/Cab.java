package com.uber.uber.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

import javax.persistence.*;


@Entity
@Table(name = "cab")
@Data
@Builder
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