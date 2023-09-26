package vn.edu.iuh.fit.backend.services;

import jakarta.inject.Inject;
import vn.edu.iuh.fit.backend.repositories.StatisticRepository;

import java.time.LocalDateTime;
import java.util.Map;

public class StatisticServices {
    @Inject
    private StatisticRepository statisticDateRepository;

    public Map<LocalDateTime, Integer> statisticsByDate() {
        return statisticDateRepository.statisticsByDate();
    }

    public Map<LocalDateTime, Integer> statisticsByPeriod(LocalDateTime startDate, LocalDateTime endDate) {
        return statisticDateRepository.statisticsByPeriod(startDate, endDate);
    }

    public Map<Long, Integer> statisticsByEmployee(LocalDateTime startDate, LocalDateTime endDate) {
        return statisticDateRepository.statisticsByEmployee(startDate, endDate);
    }
}
