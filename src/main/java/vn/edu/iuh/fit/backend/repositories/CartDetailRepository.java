package vn.edu.iuh.fit.backend.repositories;

import org.slf4j.LoggerFactory;
import vn.edu.iuh.fit.backend.models.Cart;
import vn.edu.iuh.fit.backend.models.CartDetail;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CartDetailRepository extends CRUDRepository<CartDetail> {
    public CartDetailRepository() {
        super();
        logger = LoggerFactory.getLogger(this.getClass().getName());
    }

    public long countByCustomer(long customerId) {
        try {
            return em.createNamedQuery("CartDetail.countByCustomer", Long.class)
                    .setParameter("customerId", customerId)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return 0L;
    }

    public List<CartDetail> getCartDetailsByCustomerId(long customerId) {
        try {
            return em.createNamedQuery("CartDetail.getCartDetailsByCustomerId", CartDetail.class)
                    .setParameter("customerId", customerId)
                    .getResultList();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return new ArrayList<>();
    }

    public Optional<CartDetail> findById(long productId, long cartId) {
        try {
            CartDetail cartDetail = em.createNamedQuery("CartDetail.findById", CartDetail.class)
                    .setParameter("productId", productId)
                    .setParameter("cartId", cartId)
                    .setMaxResults(1)
                    .getSingleResult();

            return Optional.of(cartDetail);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return Optional.empty();
    }

    public boolean delete(long productId, long cartId) {
        try {
            transaction.begin();

            em.createNamedQuery("CartDetail.delete")
                    .setParameter("productId", productId)
                    .setParameter("cartId", cartId)
                    .executeUpdate();

            transaction.commit();
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            transaction.rollback();
        }

        return false;
    }

    public boolean addQty(CartDetail cartDetail) {
        try {
            transaction.begin();

            int update = em.createNamedQuery("CartDetail.addQty")
                    .setParameter("qty", cartDetail.getQuantity())
                    .setParameter("productId", cartDetail.getProduct().getProduct_id())
                    .setParameter("cartId", cartDetail.getCart().getCustomer().getId())
                    .executeUpdate();

            transaction.commit();
            return update > 0;
        } catch (Exception e) {
            logger.error(e.getMessage());
            transaction.rollback();
        }

        return false;
    }

    public List<CartDetail> getByProductIds(List<Long> productIds) {
        try {
            return em.createNamedQuery("CartDetail.getByProductIds", CartDetail.class)
                    .setParameter("productIds", productIds)
                    .getResultList();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return new ArrayList<>();
    }
}
