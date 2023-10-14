package com.sgnortes.ordermanagement.customer.mapper;

import com.sgnortes.ordermanagement.customer.dto.CustomerDto;
import com.sgnortes.ordermanagement.customer.entity.Customer;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapper class for Customer
 * @author Sergio
 */
@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDto customerToCustomerDto(Customer entity);

    Customer customerDtoToCustomer(CustomerDto dto);

    List<CustomerDto> customerListToCustomerDtoList(List<Customer> entities);

    List<Customer> customerDtoListToCustomerList(List<CustomerDto> dtoList);

}
