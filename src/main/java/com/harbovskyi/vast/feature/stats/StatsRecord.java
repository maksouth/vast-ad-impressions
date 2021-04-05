package com.harbovskyi.vast.feature.stats;

import lombok.Value;

@Value
class StatsRecord {
    Integer impressionsCount;
    Integer adRequestsCount;
    Double fillRate;
}
