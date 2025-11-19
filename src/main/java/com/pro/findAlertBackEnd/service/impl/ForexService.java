package com.pro.findAlertBackEnd.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pro.findAlertBackEnd.model.request.ApplySettingsRequest;
import com.pro.findAlertBackEnd.model.response.ApplySettingsResponse;
import com.pro.findAlertBackEnd.model.response.DetailsEventResponse;
import com.pro.findAlertBackEnd.service.IForexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
@Slf4j
public class ForexService implements IForexService {
    private final WebClient webClient;

    private final ObjectMapper objectMapper = new ObjectMapper(); // Jackson

    private static final String SCRAPER_API_KEY = "d6d4bf0c0685e24e605e2362ee1c9f55";

    public ForexService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.exchangeStrategies(ExchangeStrategies.builder()
                        .codecs(configurer -> configurer
                                .defaultCodecs()
                                .maxInMemorySize(10 * 1024 * 1024)) // 10MB
                        .build())
                .build();
    }

    @Cacheable(value = "detailsEvent", key = "#eventId")
    public Mono<DetailsEventResponse> getCalendarDetailsEvent(Long eventId) {
        String scraperUrl = "https://api.scraperapi.com/?api_key=" + SCRAPER_API_KEY +
                "&url=https://www.forexfactory.com/calendar/details/1-" + eventId;
        return webClient
                .get()
                .uri(scraperUrl)
                .retrieve()
                .bodyToMono(String.class)
                .map(jsonString -> {
                    try {
                        return objectMapper.readValue(jsonString, DetailsEventResponse.class);
                    } catch (JsonProcessingException e) {
                        log.error("Failed to parse JSON for eventId: {}", eventId, e);
                        return new DetailsEventResponse();
                    }
                });
    }

    @Override
    public Mono<ApplySettingsResponse> getApplySettings(ApplySettingsRequest request) {
//        String scraperUrl = "https://api.scraperapi.com/?api_key=" + SCRAPER_API_KEY +
//                "&url=https://www.forexfactory.com/calendar/apply-settings/100000?navigation=1";
        String scraperUrl = "https://www.forexfactory.com/calendar/apply-settings/100000?navigation=1";
        return webClient
                .post()
                .uri(scraperUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(String.class)
                .map(jsonString -> {
                    try {
                        return objectMapper.readValue(jsonString, ApplySettingsResponse.class);
                    } catch (JsonProcessingException e) {
                        log.error("Failed to parse JSON to ApplySettingsResponse", e);
                        return new ApplySettingsResponse();
                    }
                });
    }
}
