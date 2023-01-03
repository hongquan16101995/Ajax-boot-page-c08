package com.example.ajax_boot_c08.repository;

import com.example.ajax_boot_c08.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findAllByNameContaining(String name);
}
