package vn.edu.iuh.fit.backend.repositories;

import org.slf4j.LoggerFactory;
import vn.edu.iuh.fit.backend.enums.EmployeeStatus;
import vn.edu.iuh.fit.backend.models.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeRepository extends CRUDRepository<Employee> {
    public EmployeeRepository() {
        super();
        logger = LoggerFactory.getLogger(this.getClass().getName());
    }

    public List<Employee> getAll(int page) {
        try {
            return em.createNamedQuery("Employee.getAll", Employee.class)
                    .setFirstResult((page - 1) * 20)
                    .setMaxResults(20)
                    .getResultList();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return new ArrayList<>();
    }

    public Optional<Employee> findById(long id) {
        try {
            return Optional.of(em.find(Employee.class, id));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return Optional.empty();
    }

    public boolean updateStatus(long id, EmployeeStatus status) {
        try {
            transaction.begin();
            int numberUpdate = em.createNamedQuery("Employee.updateStatus")
                    .setParameter("status", status)
                    .setParameter("id", id)
                    .executeUpdate();
            transaction.commit();
            return numberUpdate > 0;
        } catch (Exception e) {
            transaction.rollback();
            logger.error(e.getMessage());
        }

        return false;
    }
}
