package com.harbovskyi.vast.feature.impression;

import com.harbovskyi.vast.model.EventType;
import com.harbovskyi.vast.model.FilterType;
import com.harbovskyi.vast.feature.StatisticPropertyCounter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/impression")
@RequiredArgsConstructor
public class ImpressionController {
    private final StatisticPropertyCounter statisticPropertyCounter;

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> getAd(String sdkVersion, String sessionId, String platform,
                                        String userName, String countryCode) {
        statisticPropertyCounter.increment(EventType.IMPRESSION, FilterType.SDK, sdkVersion);
        statisticPropertyCounter.increment(EventType.IMPRESSION, FilterType.USER, userName);
        return ResponseEntity.ok().build();
    }
}
