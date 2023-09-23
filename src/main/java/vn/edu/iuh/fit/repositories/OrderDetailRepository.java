package vn.edu.iuh.fit.repositories;

import org.slf4j.LoggerFactory;
import vn.edu.iuh.fit.models.OrderDetail;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDetailRepository extends CRUDRepository<OrderDetail> {
    public OrderDetailRepository() {
        logger = LoggerFactory.getLogger(this.getClass().getName());
    }

    public List<OrderDetail> getAll(int page) {
        try {
            return em.createNamedQuery("OrderDetail.getAll", OrderDetail.class)
                    .setFirstResult((page - 1) * 20)
                    .setMaxResults(20)
                    .getResultList();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return new ArrayList<>();
    }

    public Optional<OrderDetail> findById(long orderId, long productId) {
        try {
            OrderDetail orderDetail = em.createNamedQuery("OrderDetail.findById", OrderDetail.class)
                    .setParameter("orderId", orderId)
                    .setParameter("productId", productId)
                    .getSingleResult();

            return Optional.of(orderDetail);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return Optional.empty();
    }
}
