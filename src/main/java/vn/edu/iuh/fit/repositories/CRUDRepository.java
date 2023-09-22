package vn.edu.iuh.fit.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.iuh.fit.db.Connection;

public abstract class CRUDRepository<T> {
    protected final EntityManager em;
    protected final EntityTransaction transaction;
    protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public CRUDRepository() {
        em = Connection.getInstance().getEntityManager();
        transaction = em.getTransaction();
    }

    public boolean add(T t) {
        try {
            transaction.begin();
            em.persist(t);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            logger.error(e.getMessage());
        }

        return false;
    }

    public boolean update(T t) {
        try {
            transaction.begin();
            em.merge(t);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            logger.error(e.getMessage());
        }

        return false;
    }
}
