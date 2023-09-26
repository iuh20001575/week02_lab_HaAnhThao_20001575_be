package vn.edu.iuh.fit.backend.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.slf4j.Logger;
import vn.edu.iuh.fit.backend.db.Connection;

public abstract class CRUDRepository<T> {
    protected final EntityManager em;
    protected final EntityTransaction transaction;
    protected Logger logger;

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
