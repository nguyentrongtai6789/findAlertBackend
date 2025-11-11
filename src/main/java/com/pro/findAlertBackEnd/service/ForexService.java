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
    private static final String BASE_URL = "https://www.forexfactory.com";

    private final WebClient webClient;

    private final ObjectMapper objectMapper = new ObjectMapper(); // Jackson

    public ForexService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(BASE_URL).build();
    }

    @Cacheable(value = "detailsEvent", key = "#eventId")
    public Mono<EventDetailResponse> detailsEvent(Long eventId) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/calendar/details/1-{eventId}")
                        .build(eventId))
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
