package com.harbovskyi.vast;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class StatisticPropertyCounter {
    private static final String COLON = ":";

    private final RedisTemplate<String, Long> redisTemplate;

    public void increment(EventType eventType, PropertyType propertyType, String id) {
        String key = createKey(eventType, propertyType);
        redisTemplate.opsForHash().increment(key, id, 1);
    }

    public Map<String, Long> getCountsForEventPerProperty(EventType eventType, PropertyType propertyType) {
        String key = createKey(eventType, propertyType);
        return redisTemplate.<String, Long>opsForHash().entries(key);
    }

    private String createKey(EventType eventType, PropertyType propertyType) {
        return eventType + COLON + propertyType;
    }
}
