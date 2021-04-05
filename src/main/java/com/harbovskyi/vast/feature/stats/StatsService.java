package com.harbovskyi.vast.feature.stats;

import com.harbovskyi.vast.model.EventType;
import com.harbovskyi.vast.model.FilterType;
import com.harbovskyi.vast.feature.StatisticPropertyCounter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class StatsService {
    private final StatisticPropertyCounter statisticPropertyCounter;

    public GetStatsResponse prepareAdEventsStats(FilterType filterType) {
        Map<String, Integer> adRequestCounts =
            statisticPropertyCounter.getCountsForEventPerFilter(EventType.AD, filterType);
        Map<String, Integer> impressionCounts =
            statisticPropertyCounter.getCountsForEventPerFilter(EventType.IMPRESSION, filterType);

        Set<String> ids = new HashSet<>(adRequestCounts.keySet());
        ids.addAll(impressionCounts.keySet());

        Map<String, StatsRecord> statsRecordsMap = ids.stream().map(id -> {
            Integer adRequestsCount = adRequestCounts.getOrDefault(id, 0);
            Integer impressionsCount = impressionCounts.getOrDefault(id, 0 );
            Double fillRate = adRequestsCount > 0 ? impressionsCount.doubleValue() / adRequestsCount : 0;
            StatsRecord record = new StatsRecord(impressionsCount, adRequestsCount, fillRate);
            return Pair.of(id, record);
        }).collect(Collectors.toMap(Pair::getFirst, Pair::getSecond));

        return new GetStatsResponse(filterType, statsRecordsMap);
    }
}
