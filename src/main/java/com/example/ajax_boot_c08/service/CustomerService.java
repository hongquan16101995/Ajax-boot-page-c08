package com.example.ajax_boot_c08.service;

import com.example.ajax_boot_c08.model.Customer;
import com.example.ajax_boot_c08.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private ICustomerRepository iCustomerRepository;

    public List<Customer> findAll() {
        return iCustomerRepository.findAll();
    }

    public Page<Customer> findAllPage(Pageable pageable) {
        return iCustomerRepository.findAll(pageable);
    }

    public Customer save(Customer customer) {
        return iCustomerRepository.save(customer);
    }

    public void delete(Long id) {
        iCustomerRepository.deleteById(id);
    }

    public Customer findById(Long id) {
        return iCustomerRepository.findById(id).get();
    }

    public List<Customer> findAllByName(String name) {
        return iCustomerRepository.findAllByNameContaining(name);
    }
}
