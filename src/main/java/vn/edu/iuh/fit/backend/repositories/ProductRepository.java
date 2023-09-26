package vn.edu.iuh.fit.backend.repositories;

import org.slf4j.LoggerFactory;
import vn.edu.iuh.fit.backend.enums.ProductStatus;
import vn.edu.iuh.fit.backend.models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepository extends CRUDRepository<Product> {
    public ProductRepository() {
        super();
        logger = LoggerFactory.getLogger(this.getClass().getName());
    }

    public List<Product> getAll(int page) {
        try {
            return em.createNamedQuery("Product.getAll", Product.class)
                    .setFirstResult((page - 1) * 20)
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

    public boolean updateStatus(long id, ProductStatus status) {
        try {
            transaction.begin();
            int numberUpdate = em.createNamedQuery("Product.updateStatus")
                    .setParameter("status", status)
                    .setParameter("product_id", id)
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
