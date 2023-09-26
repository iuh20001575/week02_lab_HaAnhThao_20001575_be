package vn.edu.iuh.fit.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.iuh.fit.db.Connection;
import vn.edu.iuh.fit.models.StatisticDate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class StatisticDateRepository {
    private final EntityManager em;
    private final EntityTransaction transaction;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public StatisticDateRepository() {
        em = Connection.getInstance().getEntityManager();
        transaction = em.getTransaction();
    }

    public Map<LocalDateTime, Integer> statisticsByDate() {
        try {
            return (Map<LocalDateTime, Integer>) em.createNamedQuery("Order.statisticsByDate").getResultList().parallelStream().collect(Collectors.toMap((Object[] objects) -> {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
                return LocalDateTime.parse(objects[0].toString(), formatter);
            }, (Object[] objects) -> ((Number) objects[1]).intValue(), Integer::sum, TreeMap::new));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return new TreeMap<>();
    }



    public Map<LocalDateTime, Integer> statisticsByPeriod(LocalDateTime startDate, LocalDateTime endDate) {
        try {
            return (Map<LocalDateTime, Integer>) em.createNamedQuery("Order.statisticsByPeriod")
                    .setParameter(1, startDate)
                    .setParameter(2, endDate)
                    .getResultList()
                    .parallelStream()
                    .collect(Collectors.toMap((Object[] objects) -> {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
                        return LocalDateTime.parse(objects[0].toString(), formatter);
                    }, (Object[] objects) -> ((Number) objects[1]).intValue(), Integer::sum, TreeMap::new));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return new TreeMap<>();
    }
}
