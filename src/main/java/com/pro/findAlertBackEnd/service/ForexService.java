package com.pro.findAlertBackEnd.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pro.findAlertBackEnd.model.response.EventDetailResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
@Slf4j
public class ForexService {
    private final WebClient webClient;

    private final ObjectMapper objectMapper = new ObjectMapper(); // Jackson

    private static final String SCRAPER_API_KEY = "d6d4bf0c0685e24e605e2362ee1c9f55";


    public ForexService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    @Cacheable(value = "detailsEvent", key = "#eventId")
    public Mono<EventDetailResponse> detailsEvent(Long eventId) {
        String scraperUrl = "https://api.scraperapi.com/?api_key=" + SCRAPER_API_KEY +
                "&url=https://www.forexfactory.com/calendar/details/1-" + eventId;
        return webClient
                .get()
                .uri(scraperUrl)
                .retrieve()
                .bodyToMono(String.class)
                .map(jsonString -> {
                    try {
                        return objectMapper.readValue(jsonString, EventDetailResponse.class);
                    } catch (JsonProcessingException e) {
                        log.error("Failed to parse JSON for eventId: {}", eventId, e);
                        return new EventDetailResponse();
                    }
                });
    }
}
