package com.sgnortes.ordermanagement.customer.service;

import com.sgnortes.ordermanagement.common.constants.ErrorMessages;
import com.sgnortes.ordermanagement.common.dto.PageDto;
import com.sgnortes.ordermanagement.customer.dto.CustomerDto;
import com.sgnortes.ordermanagement.customer.dto.CustomerPagingDto;
import com.sgnortes.ordermanagement.customer.entity.Customer;
import com.sgnortes.ordermanagement.customer.mapper.CustomerMapper;
import com.sgnortes.ordermanagement.customer.repository.interfaces.CustomerRepository;
import com.sgnortes.ordermanagement.customer.service.impl.CustomerServiceImpl;
import com.sgnortes.ordermanagement.customer.service.interfaces.CustomerService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Testing class for CustomerService
 * @author Sergio
 */
@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    private CustomerService customerService;
    @Mock
    private CustomerRepository customerRepository;
    @Spy
    private CustomerMapper customerMapper;

    @BeforeEach
    void initTest(){
        customerMapper = Mappers.getMapper(CustomerMapper.class);
        customerService = new CustomerServiceImpl(customerRepository, customerMapper);
    }

    @Test
    public void shouldFindAll(){
        // GIVEN
        Pageable pageable = PageRequest.of(0, 1);
        Customer customer = getCustomer();
        Page page = new PageImpl<>(List.of(customer),pageable, 1);
        when(customerRepository.findAll(pageable)).thenReturn(page);

        // WHEN
        PageDto<CustomerDto> response = customerService.findAll(pageable);

        // THEN
        assertEquals(customer.getName(), response.getData().get(0).getName(), "Names should match.");
        assertEquals(customer.getEmail(), response.getData().get(0).getEmail(), "Emails should match.");
    }

    @Test
    public void shouldCreate() {
        // GIVEN
        CustomerDto customer = getCustomerDto();
        doNothing().when(customerRepository).persist(any());

        // WHEN
        customerService.create(customer);

        // THEN
        verify(customerRepository, times(1)).persist(any());
    }

    @Test
    public void shouldUpdate(){
        // GIVEN
        Customer customer = getCustomer();
        CustomerDto customerDto = getCustomerDto();
        when(customerRepository.findById(any())).thenReturn(Optional.of(customer));
        doNothing().when(customerRepository).merge(any());

        // WHEN
        customerService.update(customerDto);

        // THEN
        verify(customerRepository, times(1)).findById(any());
        verify(customerRepository, times(1)).merge(any());
    }

    @Test
    public void shouldThrowExceptionWhenUpdatingIfIdDoesNotExist(){
        // GIVEN
        CustomerDto customerDto = getCustomerDto();
        when(customerRepository.findById(any())).thenReturn(Optional.empty()); // We simulate id doesn't exist

        // WHEN
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            customerService.update(customerDto);
        }, "EntityNotFoundException was expected to be thrown");

        // THEN
        assertEquals(ErrorMessages.ENTITY_NOT_EXISTS.getMessage(), exception.getMessage());
    }

    @Test
    public void shouldBatchUpdate(){
        // GIVEN
        CustomerDto customerDto = getCustomerDto();
        Customer customer = getCustomer();
        when(customerRepository.findAllById(any())).thenReturn(List.of(customer));
        doNothing().when(customerRepository).batchUpdate(any());

        // WHEN
        customerService.batchUpdate(List.of(customerDto));

        // THEN
        verify(customerRepository, times(1)).batchUpdate(any());
    }

    @Test
    public void shouldFindAllPaginated(){
        // GIVEN
        CustomerPagingDto dto = new CustomerPagingDto();
        dto.setPage(0);
        dto.setSize(1);
        dto.setSort(new String[]{"id:asc"});

        Page page = new PageImpl<>(List.of(getCustomer()),PageRequest.of(0, 1), 1);
        when(customerRepository.findAllPaginatedFilteredAndSorted(any(), any())).thenReturn(page);

        // WHEN
        PageDto response = customerService.findAllPaginatedFilteredAndSorted(dto);

        // THEN
        assertNotNull(response);
        verify(customerRepository, times(1)).findAllPaginatedFilteredAndSorted(any(), any());
    }

    @ParameterizedTest()
    @MethodSource("provideFindAllTestInputsToEmulateException")
    public void shouldThrowExceptionWhenFindAllPaginated(CustomerPagingDto dto){

        // WHEN
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            customerService.findAllPaginatedFilteredAndSorted(dto);
        }, "IllegalArgumentException was expected to be thrown");

        // THEN
        if(Objects.isNull(dto.getPage())  || dto.getPage().equals(-1))
            assertEquals(ErrorMessages.NUMBER_PAGE_NOT_VALID.getMessage(), exception.getMessage(), "Error message is not expected.");
        else if(Objects.isNull(dto.getSize().equals(null))  || dto.getSize().equals(-1))
            assertEquals(ErrorMessages.SIZE_PAGE_NOT_VALID.getMessage(), exception.getMessage(), "Error message is not expected.");
        else if(Objects.isNull(dto.getSort().equals(null))  || dto.getSort().equals(new String[0]))
            assertEquals(ErrorMessages.ORDER_DOESNT_EXIST.getMessage(), "Error message is not expected.");
    }

    private static Stream<CustomerPagingDto> provideFindAllTestInputsToEmulateException(){
        CustomerPagingDto testNullPageDto = new CustomerPagingDto();
        testNullPageDto.setPage(null);

        CustomerPagingDto testNegativePageDto = new CustomerPagingDto();
        testNegativePageDto.setPage(-1);

        CustomerPagingDto testNullSizeDto = new CustomerPagingDto();
        testNullSizeDto.setSize(null);

        CustomerPagingDto testNegativeSizeDto = new CustomerPagingDto();
        testNegativeSizeDto.setSize(-1);

        CustomerPagingDto testNullSortDto = new CustomerPagingDto();
        testNullSortDto.setSort(null);

        CustomerPagingDto testEmptySortDto = new CustomerPagingDto();
        testEmptySortDto.setSort(new String[0]);

        return Stream.of(testNullPageDto, testNegativePageDto, testNullSizeDto, testNegativeSizeDto, testNullSortDto, testEmptySortDto);
    }

    private Customer getCustomer() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Sergio");
        customer.setEmail("sergio@gmail.com");
        return customer;
    }

    private CustomerDto getCustomerDto() {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(1L);
        customerDto.setName("Sergio");
        customerDto.setEmail("sergio@gmail.com");
        return customerDto;
    }

}
