package com.sgnortes.ordermanagement.customer.service.impl;

import com.sgnortes.ordermanagement.common.constants.ErrorMessages;
import com.sgnortes.ordermanagement.common.dto.PageDto;
import com.sgnortes.ordermanagement.common.utils.PaginationUtils;
import com.sgnortes.ordermanagement.customer.dto.CustomerDto;
import com.sgnortes.ordermanagement.customer.dto.CustomerPagingDto;
import com.sgnortes.ordermanagement.customer.entity.Customer;
import com.sgnortes.ordermanagement.customer.mapper.CustomerMapper;
import com.sgnortes.ordermanagement.customer.repository.interfaces.CustomerRepository;
import com.sgnortes.ordermanagement.customer.service.interfaces.CustomerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.Specification.where;

/**
 * Implementation of customer service.
 * @author Sergio
 */
@Service
@Transactional(readOnly = true)
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public PageDto<CustomerDto> findAll(Pageable pageable) {
        Page<Customer> page = customerRepository.findAll(pageable);

        return new PageDto<>(
                customerMapper.customerListToCustomerDtoList(page.getContent()),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages()
        );

    }

    @Override
    @Transactional
    public void create(CustomerDto dto){
        customerRepository.persist(customerMapper.customerDtoToCustomer(dto));
    }

    @Override
    @Transactional
    public void update(CustomerDto dto){
        customerRepository.findById(dto.getId()).orElseThrow(() -> { throw new EntityNotFoundException(ErrorMessages.ENTITY_NOT_EXISTS.getMessage()); });
        customerRepository.merge(customerMapper.customerDtoToCustomer(dto));
    }

    @Override
    @Transactional
    public void batchUpdate(List<CustomerDto> dtos){

        List<Customer> customersInDB = customerRepository.findAllById(dtos.stream().map(dto -> dto.getId()).collect(Collectors.toList()));
        List<Long> idsInDB = customersInDB.stream().map(customer -> customer.getId()).collect(Collectors.toList());

        List<Customer> customersToUpdate = dtos.stream().filter(dto -> idsInDB.contains(dto.getId())).map(
                dto -> customerMapper.customerDtoToCustomer(dto)
        ).collect(Collectors.toList());

        customerRepository.batchUpdate(customersToUpdate);

    }

    @Override
    public PageDto<CustomerDto> findAllPaginatedFilteredAndSorted(CustomerPagingDto dto) {

        if(Objects.isNull(dto.getPage()) || dto.getPage() < 0 ){
            throw new IllegalArgumentException(ErrorMessages.NUMBER_PAGE_NOT_VALID.getMessage());
        }

        if(Objects.isNull(dto.getSize()) || dto.getSize() < 0){
            throw new IllegalArgumentException(ErrorMessages.SIZE_PAGE_NOT_VALID.getMessage());
        }

        if(Objects.isNull(dto.getSort()) || dto.getSort().length == 0){
            throw new IllegalArgumentException(ErrorMessages.ORDER_DOESNT_EXIST.getMessage());
        }

        Page<Customer> page = customerRepository.findAllPaginatedFilteredAndSorted(
                createSpecification(dto),
                PageRequest.of(dto.getPage(),
                        dto.getSize(),
                        PaginationUtils.createSort(dto.getSort())
                ));

        return new PageDto<>(
                customerMapper.customerListToCustomerDtoList(page.getContent()),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages()
        );

    }

    private Specification<Object> createSpecification(CustomerPagingDto dto){

        return  where(PaginationUtils.createSpecification(dto.getId(), "id", Long.class))
                .and(PaginationUtils.createSpecification(dto.getName(), "name", String.class))
                .and(PaginationUtils.createSpecification(dto.getSurname(), "surname", String.class))
                .and(PaginationUtils.createSpecification(dto.getCountry(), "country", String.class))
                .and(PaginationUtils.createSpecification(dto.getAddress(), "address", String.class))
                .and(PaginationUtils.createSpecification(dto.getPostalCode(), "postalCode", String.class))
                .and(PaginationUtils.createSpecification(dto.getCity(), "city", String.class))
                .and(PaginationUtils.createSpecification(dto.getEmail(), "email", String.class));

    }
}
