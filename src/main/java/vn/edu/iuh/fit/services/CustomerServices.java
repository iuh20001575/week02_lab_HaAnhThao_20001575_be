package vn.edu.iuh.fit.services;

import jakarta.inject.Inject;
import vn.edu.iuh.fit.models.Customer;
import vn.edu.iuh.fit.models.Employee;
import vn.edu.iuh.fit.repositories.CustomerRepository;
import vn.edu.iuh.fit.repositories.EmployeeRepository;

import java.util.List;
import java.util.Optional;

public class CustomerServices {
    @Inject
    private CustomerRepository customerRepository;

    public List<Customer> getAll(int page) {
        return customerRepository.getAll(Math.max(1, page));
    }

    public Optional<Customer> findById(long id) {
        return customerRepository.findById(id);
    }

    public boolean add(Customer customer) {
        return customerRepository.add(customer);
    }

    public Optional<Boolean> update(Customer customer) {
        Optional<Customer> optional = findById(customer.getId());

        if (optional.isEmpty())
            return Optional.empty();

        return Optional.of(customerRepository.update(customer));
    }
}
