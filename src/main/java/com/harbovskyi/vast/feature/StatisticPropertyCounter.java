package com.harbovskyi.vast.feature;

import com.harbovskyi.vast.model.EventType;
import com.harbovskyi.vast.model.FilterType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class StatisticPropertyCounter {
    private static final String COLON = ":";

    private final RedisTemplate<String, Long> redisTemplate;

    public void increment(EventType eventType, FilterType filterType, String id) {
        String key = createKey(eventType, filterType);
        redisTemplate.opsForHash().increment(key, id, 1);
    }

    public Map<String, Integer> getCountsForEventPerFilter(EventType eventType, FilterType filterType) {
        String key = createKey(eventType, filterType);
        return redisTemplate.<String, Integer>opsForHash().entries(key);
    }

    private String createKey(EventType eventType, FilterType filterType) {
        return eventType + COLON + filterType;
    }
}
