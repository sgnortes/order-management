package com.sgnortes.ordermanagement.customer.repository.interfaces.jpa;

import com.sgnortes.ordermanagement.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Jpa repository interface for Customer entity.
 * @author Sergio
 */
@Repository
public interface CustomerJpaRepository extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {
}
