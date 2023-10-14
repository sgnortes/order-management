package com.sgnortes.ordermanagement.customer.repository.impl;

import com.sgnortes.ordermanagement.common.properties.JPAProperties;
import com.sgnortes.ordermanagement.customer.entity.Customer;
import com.sgnortes.ordermanagement.customer.repository.interfaces.CustomerRepository;
import com.sgnortes.ordermanagement.customer.repository.interfaces.jpa.CustomerJpaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Customer repository implementation.
 * @author Sergio
 */
@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    private final JPAProperties jpaProperties;

    private final CustomerJpaRepository customerJpaRepository;

    public CustomerRepositoryImpl(EntityManager entityManager, JPAProperties jpaProperties, CustomerJpaRepository customerJpaRepository) {
        this.entityManager = entityManager;
        this.jpaProperties = jpaProperties;
        this.customerJpaRepository = customerJpaRepository;
    }

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return customerJpaRepository.findAll(pageable);
    }

    @Override
    public Page<Customer> findAllPaginatedFilteredAndSorted(Specification spec, Pageable pageable){
        return customerJpaRepository.findAll(spec, pageable);
    }

    @Override
    public Optional<Customer> findById(Long id){
        return customerJpaRepository.findById(id);
    }

    @Override
    public List<Customer> findAllById(List<Long> ids){
        return customerJpaRepository.findAllById(ids);
    }

    @Override
    public void persist(Customer entity){
        entityManager.persist(entity);
    }

    @Override
    public void merge(Customer customer){
        entityManager.merge(customer);
    }

    @Override
    public void deleteById(Long id){
        customerJpaRepository.deleteById(id);
    }

    @Override
    public void batchUpdate(List<Customer> customers){
        for(long i = 0; i < customers.size(); i++){
            // Once we have reached the batch size we flush the changes into the database and clean the PersistenceContext
            if(i > 0 && i % jpaProperties.getBatchSize() == 0){
                entityManager.flush();
                entityManager.clear();
            }
            entityManager.merge(customers.get(Integer.valueOf((int) i)));
        }
    }
}
