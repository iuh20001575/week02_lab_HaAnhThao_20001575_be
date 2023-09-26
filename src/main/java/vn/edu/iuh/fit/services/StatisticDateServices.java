package vn.edu.iuh.fit.services;

import jakarta.inject.Inject;
import vn.edu.iuh.fit.models.StatisticDate;
import vn.edu.iuh.fit.repositories.StatisticDateRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class StatisticDateServices {
    @Inject
    private StatisticDateRepository statisticDateRepository;

    public Map<LocalDateTime, Integer> statisticsByDate() {
        return statisticDateRepository.statisticsByDate();
    }

    public Map<LocalDateTime, Integer> statisticsByPeriod(LocalDateTime startDate, LocalDateTime endDate) {
        return statisticDateRepository.statisticsByPeriod(startDate, endDate);
    }
}
