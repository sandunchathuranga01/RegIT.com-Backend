package com.RegIT.RegIT.Backend.service;


import com.RegIT.RegIT.Backend.model.Customer;
import com.RegIT.RegIT.Backend.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    public Customer createCustomer(Customer customer) {
        return customerRepo.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    public Optional<Customer> getCustomerById(String id) {
        return customerRepo.findById(id);
    }

    public Customer updateCustomer(String id, Customer customerDetails) {
        Optional<Customer> customer = customerRepo.findById(id);

        if (customer.isPresent()) {
            Customer existingCustomer = customer.get();
            existingCustomer.setProductID(customerDetails.getProductID());
            existingCustomer.setNicID(customerDetails.getNicID());
            existingCustomer.setCountry(customerDetails.getCountry());
            existingCustomer.setPhoneNumber(customerDetails.getPhoneNumber());
            existingCustomer.setEmail(customerDetails.getEmail());
            existingCustomer.setAddress(customerDetails.getAddress());
            existingCustomer.setRemark(customerDetails.getRemark());
            return customerRepo.save(existingCustomer);
        }
        return null;
    }

    public void deleteCustomer(String id) {
        customerRepo.deleteById(id);
    }
}
