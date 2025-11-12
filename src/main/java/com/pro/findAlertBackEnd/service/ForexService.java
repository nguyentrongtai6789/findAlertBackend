package com.pro.findAlertBackEnd.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pro.findAlertBackEnd.model.response.EventDetailResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Service
@Slf4j
public class ForexService {
    private static final String BASE_URL = "https://www.forexfactory.com";

    private final WebClient webClient;

    private final ObjectMapper objectMapper = new ObjectMapper(); // Jackson

    public ForexService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl(BASE_URL)
                .defaultHeader(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36")
                .build();
    }

    @Cacheable(value = "detailsEvent", key = "#eventId")
    public Mono<EventDetailResponse> detailsEvent(Long eventId) throws UnknownHostException {
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
