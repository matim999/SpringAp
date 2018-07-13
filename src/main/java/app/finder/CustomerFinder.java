package app.finder;

import app.entity.Customer;
import app.exceptions.MyNotFoundException;
import app.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static app.ErrorCode.DIFFERENT;

@Component
public class CustomerFinder {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerFinder(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> findAllCustomer() {
        return customerRepository.findAll();
    }

    public Customer findCustomerById(int id) {
        return customerRepository.findById(id).orElseThrow(() -> new MyNotFoundException("Category with given Id not found", DIFFERENT));
    }

    public List<Customer> findAllCustomerByFirstName(String fname) {
        return customerRepository.findAllByFirstName(fname).orElseThrow(() -> new MyNotFoundException("Category with given name not found", DIFFERENT));
    }
}
