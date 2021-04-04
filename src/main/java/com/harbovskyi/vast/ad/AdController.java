package com.harbovskyi.vast.ad;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/ad")
@RequiredArgsConstructor
public class AdController {
    private final RestTemplate restTemplate;
    @Value("${ad.vast-ad-endpoint}")
    private String vastAdEndpoint;

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> getAd(String sdkVersion, String sessionId, String platform,
                                String userName, String countryCode) {
        return restTemplate.getForEntity(vastAdEndpoint, String.class);
    }
}
