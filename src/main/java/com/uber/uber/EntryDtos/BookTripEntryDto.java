package com.uber.uber.EntryDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookTripEntryDto {
    private int customerId;
    private String fromLocation;

    private String toLocation;

    private int distanceInKm;
}
