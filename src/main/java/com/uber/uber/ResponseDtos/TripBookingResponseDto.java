package com.uber.uber.ResponseDtos;

import com.uber.uber.enums.TripStatus;
import com.uber.uber.models.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TripBookingResponseDto {
    private int tripBookingId;

    private String fromLocation;

    private String toLocation;

    private int distanceInKm;

    private TripStatus status;

    private int bill;

    private int customerId;
}
