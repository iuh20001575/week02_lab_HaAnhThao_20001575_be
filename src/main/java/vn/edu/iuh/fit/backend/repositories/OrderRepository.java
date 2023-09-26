package vn.edu.iuh.fit.backend.repositories;

import org.slf4j.LoggerFactory;
import vn.edu.iuh.fit.backend.models.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderRepository extends CRUDRepository<Order> {
    public OrderRepository() {
        super();
        logger = LoggerFactory.getLogger(this.getClass().getName());
    }

    public List<Order> getAll(int page) {
        try {
            return em.createNamedQuery("Order.getAll", Order.class)
                    .setFirstResult((page - 1) * 20)
                    .setMaxResults(20)
                    .getResultList();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return new ArrayList<>();
    }

    public Optional<Order> findById(long id) {
        try {
            return Optional.of(em.find(Order.class, id));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return Optional.empty();
    }
}
