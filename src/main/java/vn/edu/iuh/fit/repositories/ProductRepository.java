package vn.edu.iuh.fit.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.iuh.fit.db.Connection;
import vn.edu.iuh.fit.models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepository {
    private EntityManager em;
    private EntityTransaction transaction;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public ProductRepository() {
        em = Connection.getInstance().getEntityManager();
        transaction = em.getTransaction();
    }

    public List<Product> getAll(int page) {
        try {
            return em.createNamedQuery("Product.getAll", Product.class)
                    .setFirstResult((page-1) * 20)
                    .setMaxResults(20)
                    .getResultList();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return new ArrayList<>();
    }

    public Optional<Product> findById(long id) {
        try {
            return Optional.of(em.find(Product.class, id));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return Optional.empty();
    }
}
