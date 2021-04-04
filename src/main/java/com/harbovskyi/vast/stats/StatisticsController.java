package com.harbovskyi.vast.stats;

import com.harbovskyi.vast.EventType;
import com.harbovskyi.vast.PropertyType;
import com.harbovskyi.vast.StatisticPropertyCounter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/stats")
@RequiredArgsConstructor
public class StatisticsController {
    private final StatisticPropertyCounter statisticPropertyCounter;

    @GetMapping
    Map<String, Long> getStats(PropertyType filterType) {
        Map<String, Long> counts = statisticPropertyCounter.getCountsForEventPerProperty(EventType.AD, filterType);
        return counts;
    }
}
