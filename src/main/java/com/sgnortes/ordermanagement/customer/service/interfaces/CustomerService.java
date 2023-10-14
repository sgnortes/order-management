package com.sgnortes.ordermanagement.customer.service.interfaces;

import com.sgnortes.ordermanagement.common.dto.PageDto;
import com.sgnortes.ordermanagement.customer.dto.CustomerDto;
import com.sgnortes.ordermanagement.customer.dto.CustomerPagingDto;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Interface for Customer service.
 * @author Sergio
 */
public interface CustomerService {

    /**
     * Method used to find all customers using pagination.
     * @param pageable
     * @return page of CustomerDto
     */
    PageDto<CustomerDto> findAll(Pageable pageable);

    /**
     * Method used to insert customer
     * @param dto
     */
    void create(CustomerDto dto);

    /**
     * Method used to update customer
     * @param dto
     */
    @Transactional
    void update(CustomerDto dto);

    /**
     * Method used to update customers if they exist in database. If they don't exist no action will be done.
     * @param dtos list of CustomerDto
     */
    @Transactional
    void batchUpdate(List<CustomerDto> dtos);

    /**
     * Method used to retrieve data in a paginated, filtered and sorted way.
     * @param dto - dto for search
     * @return paginated response
     */
    PageDto<CustomerDto> findAllPaginatedFilteredAndSorted(CustomerPagingDto dto);
}
