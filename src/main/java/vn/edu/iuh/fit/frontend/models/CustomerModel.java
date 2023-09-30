package vn.edu.iuh.fit.frontend.models;

import vn.edu.iuh.fit.backend.models.Customer;
import vn.edu.iuh.fit.backend.services.CustomerServices;

import java.util.Optional;

public class CustomerModel {
    private final CustomerServices customerServices;

    public CustomerModel() {
        customerServices = new CustomerServices();
    }

    public Optional<Customer> login(String phone, String password) {
        return customerServices.login(phone, password);
    }
}
