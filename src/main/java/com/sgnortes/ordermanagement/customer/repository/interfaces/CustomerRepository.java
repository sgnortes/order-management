package com.sgnortes.ordermanagement.customer.repository.interfaces;

import com.sgnortes.ordermanagement.customer.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

/**
 * Interface that defines repository methods for Customer entity.
 * @author Sergio
 */
public interface CustomerRepository {

    /**
     * Method that returns customers using pagination
     * @param pageable
     * @return page with customer data
     */
    Page<Customer> findAll(Pageable pageable);

    /**
     * Method used to retrieve data in a paginated, filtered and sorted way.
     * @param spec specs to filter data
     * @param pageable pageable to search data
     * @return paginated response
     */
    Page<Customer> findAllPaginatedFilteredAndSorted(Specification spec, Pageable pageable);

    /**
     * Method that returns a customer by its id
     * @param id of the customer
     * @return Optional of Customer
     */
    Optional<Customer> findById(Long id);

    /**
     * Method that finds customers by id
     * @param ids list of ids
     * @return list of Customers
     */
    List<Customer> findAllById(List<Long> ids);

    /**
     * Method that persists entity.
     * @param entity
     */
    void persist(Customer entity);

    /**
     * Method that merges entity.
     * @param entity
     */
    void merge(Customer entity);

    /**
     * Method that deletes customer by Id
     * @param id from customer
     */
    void deleteById(Long id);

    /**
     * Method that applies batching to update massively customers in database.
     * @param customers
     */
    void batchUpdate(List<Customer> customers);
}
