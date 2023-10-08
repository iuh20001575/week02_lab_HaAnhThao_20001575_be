package vn.edu.iuh.fit.backend.repositories;

import org.slf4j.LoggerFactory;
import vn.edu.iuh.fit.backend.enums.ProductStatus;
import vn.edu.iuh.fit.backend.models.ProductPrice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class ProductPriceRepository extends CRUDRepository<ProductPrice> {

    public ProductPriceRepository() {
        super();
        logger = LoggerFactory.getLogger(this.getClass().getName());
    }

    public List<ProductPrice> getAll(int page) {
        try {
            return em.createNamedQuery("ProductPrice.getAll", ProductPrice.class)
                    .setFirstResult((page - 1) * 20)
                    .setMaxResults(20)
                    .getResultList();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return new ArrayList<>();
    }

    public Optional<ProductPrice> findById(LocalDateTime price_date_time, long productId) {
        try {
            ProductPrice productPrice = em.createNamedQuery("ProductPrice.findById", ProductPrice.class)
                    .setParameter("price_date_time", price_date_time)
                    .setParameter("productId", productId)
                    .getSingleResult();

            return Optional.of(productPrice);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return Optional.empty();
    }

    public Optional<ProductPrice> getProductNewPrice(long productId) {
        try {
            ProductPrice productPrice = em.createNamedQuery("ProductPrice.getProductNewPrice", ProductPrice.class)
                    .setParameter("productId", productId)
                    .setMaxResults(1)
                    .getSingleResult();

            return Optional.of(productPrice);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return Optional.empty();
    }

    public List<ProductPrice> getActiveProductsWithNewPrice(int page) {
        try {
            return em.createNamedQuery("ProductPrice.getActiveProductsWithNewPrice", ProductPrice.class)
                    .setParameter("status", ProductStatus.ACTIVE)
                    .setFirstResult((page - 1) * 20)
                    .setMaxResults(20)
                    .getResultList();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return new ArrayList<>();
    }

    public long countActiveProductsWithNewPrice() {
        try {
            return em
                    .createNamedQuery("ProductPrice.countActiveProductsWithNewPrice", Long.class)
                    .setParameter("status", ProductStatus.ACTIVE)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return 0;
    }

    public List<ProductPrice> getActiveProductsWithNewPriceByProductIds(List<Long> productIds) {
        try {
            return em.createNamedQuery("ProductPrice.getActiveProductsWithNewPriceByProductIds", ProductPrice.class)
                    .setParameter("productIds", productIds)
                    .getResultList();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return new ArrayList<>();
    }

    public Map<LocalDateTime, Double> getDateAndPriceByProductId(long productId) {
        try {
            return (Map<LocalDateTime, Double>) em.createNamedQuery("ProductPrice.getDateAndPriceByProductId")
                    .setParameter("productId", productId)
                    .getResultList()
                    .parallelStream()
                    .collect(Collectors.toMap((Object[] objects) -> {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
                        return LocalDateTime.parse(objects[0].toString(), formatter);
                    }, (Object[] objects) -> ((Number) objects[1]).doubleValue(), Double::sum, TreeMap::new));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }

        return new TreeMap<>();
    }
}
