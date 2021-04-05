package com.harbovskyi.vast.feature.stats;

import com.harbovskyi.vast.model.FilterType;
import lombok.Value;

import java.util.Map;

@Value
class GetStatsResponse {
    FilterType filterType;
    Map<String, StatsRecord> records;
}
