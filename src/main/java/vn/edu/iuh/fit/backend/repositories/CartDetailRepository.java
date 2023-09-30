package vn.edu.iuh.fit.backend.repositories;

import org.slf4j.LoggerFactory;
import vn.edu.iuh.fit.backend.models.Cart;

public class CartDetailRepository extends CRUDRepository<Cart> {
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
}
