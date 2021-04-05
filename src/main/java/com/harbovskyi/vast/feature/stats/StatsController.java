package com.harbovskyi.vast.feature.stats;

import com.harbovskyi.vast.model.FilterType;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stats")
@RequiredArgsConstructor
public class StatsController {
    private final StatsService statsService;

    @GetMapping
    GetStatsResponse getStats(FilterType filterType) {
        return statsService.prepareAdEventsStats(filterType);
    }
}
