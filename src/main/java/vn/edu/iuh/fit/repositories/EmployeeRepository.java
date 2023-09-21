package vn.edu.iuh.fit.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.iuh.fit.db.Connection;
import vn.edu.iuh.fit.enums.EmployeeStatus;
import vn.edu.iuh.fit.models.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeRepository {
    private final EntityManager em;
    private final EntityTransaction transaction;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public EmployeeRepository() {
        em = Connection.getInstance().getEntityManager();
        transaction = em.getTransaction();
    }

    public List<Employee> getAll(int page) {
        try {
            return em.createNamedQuery("Employee.getAll", Employee.class)
                    .setFirstResult((page-1) * 20)
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

    public boolean add(Employee employee) {
        try {
            transaction.begin();
            em.persist(employee);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            logger.error(e.getMessage());
        }

        return false;
    }

    public boolean update(Employee employee) {
        try {
            transaction.begin();
            em.merge(employee);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            logger.error(e.getMessage());
        }

        return false;
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
