package vn.edu.iuh.fit.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.iuh.fit.db.Connection;
import vn.edu.iuh.fit.models.ProductImage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductImageRepository {
    private final EntityManager em;
    private final EntityTransaction transaction;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public ProductImageRepository() {
        em = Connection.getInstance().getEntityManager();
        transaction = em.getTransaction();
    }

    public List<ProductImage> getAll(int page) {
        try {
            return em.createNamedQuery("ProductImage.getAll", ProductImage.class)
                    .setFirstResult((page-1) * 20)
                    .setMaxResults(20)
                    .getResultList();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return new ArrayList<>();
    }

    public Optional<ProductImage> findById(long id) {
        try {
            return Optional.of(em.find(ProductImage.class, id));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return Optional.empty();
    }

    public boolean add(ProductImage productImage) {
        try {
            transaction.begin();
            em.persist(productImage);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            logger.error(e.getMessage());
        }

        return false;
    }

    public boolean update(ProductImage productImage) {
        try {
            transaction.begin();
            em.merge(productImage);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            logger.error(e.getMessage());
        }

        return false;
    }

    public List<ProductImage> getByProductId(long productId) {
        return em.createNamedQuery("ProductImage.getByProductId", ProductImage.class)
                .setParameter("productId", productId)
                .getResultList();
    }
}
