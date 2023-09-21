package vn.edu.iuh.fit.services;

import jakarta.inject.Inject;
import vn.edu.iuh.fit.enums.EmployeeStatus;
import vn.edu.iuh.fit.models.Employee;
import vn.edu.iuh.fit.repositories.EmployeeRepository;

import java.util.List;
import java.util.Optional;

public class EmployeeServices {
    @Inject
    private EmployeeRepository employeeRepository;

    public List<Employee> getAll(int page) {
        return employeeRepository.getAll(Math.max(1, page));
    }

    public Optional<Employee> findById(long id) {
        return employeeRepository.findById(id);
    }

    public boolean add(Employee employee) {
        return employeeRepository.add(employee);
    }

    public Optional<Boolean> update(Employee employee) {
        Optional<Employee> optional = findById(employee.getId());

        if (optional.isEmpty())
            return Optional.empty();

        return Optional.of(employeeRepository.update(employee));
    }

    public Optional<Boolean> delete(long id) {
        Optional<Employee> employee = findById(id);

        if (employee.isEmpty())
            return Optional.empty();

        return Optional.of(employeeRepository.updateStatus(id, EmployeeStatus.QUITTED));
    }
}
