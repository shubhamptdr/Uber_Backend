package com.uber.uber.convertors;

import com.uber.uber.ResponseDtos.CustomerResponseDto;
import com.uber.uber.models.Customer;

public class CustomerConvertor {
    public static CustomerResponseDto convertCustomerEntityToDto(Customer customer){
        return CustomerResponseDto.builder()
                .customerId(customer.getCustomerId())
                .mobile(customer.getMobile())
                .name(customer.getName())
                .build();
    }
}
