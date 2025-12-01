package com.pro.findAlertBackEnd.service.impl;

import com.pro.findAlertBackEnd.model.request.ApplySettingsRequest;
import com.pro.findAlertBackEnd.model.response.ApplySettingsResponse;
import com.pro.findAlertBackEnd.model.response.DetailsEventResponse;
import com.pro.findAlertBackEnd.model.response.HistoryEventResponse;
import com.pro.findAlertBackEnd.service.IForexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${scraper.api.key}")
    private String SCRAPER_API_KEY;

    @Value("${scraper.url}")
    private String SCRAPER_URL;

    @Value("${forex.url}")
    private String FOREX_URL;

    public ForexService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.exchangeStrategies(ExchangeStrategies.builder()
                        .codecs(configurer -> configurer
                                .defaultCodecs()
                                .maxInMemorySize(10 * 1024 * 1024))
                        .build())
                .build();
    }

    @Cacheable(value = "detailsEvent", key = "#eventId")
    @Override
    public Mono<DetailsEventResponse> getCalendarDetailsEvent(Long eventId) {
//        String url = SCRAPER_URL + "/?api_key=" + SCRAPER_API_KEY +
//                "&url=" + FOREX_URL + "/calendar/details/1-" + eventId;
        String url = FOREX_URL + "/calendar/details/1-" + eventId;
        return webClient
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(DetailsEventResponse.class)
                .onErrorResume(e -> {
                    log.error("Failed to fetch DetailsEventResponse: ", e);
                    return Mono.just(new DetailsEventResponse());
                });
    }

    @Cacheable(value = "applySettings", key = "#request")
    @Override
    public Mono<ApplySettingsResponse> getApplySettings(ApplySettingsRequest request) {
//        String url = SCRAPER_URL + "/?api_key=" + SCRAPER_API_KEY +
//                "&url=" + FOREX_URL + "/calendar/apply-settings/100000?navigation=1";
        String url = FOREX_URL + "/calendar/apply-settings/100000?navigation=1";
        return webClient
                .post()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(ApplySettingsResponse.class)
                .onErrorResume(e -> {
                    log.error("Failed to fetch ApplySettingsResponse: ", e);
                    return Mono.just(new ApplySettingsResponse());
                });
    }

    @Cacheable(value = "historyEvent", key = "#eventId + '-' + #page")
    @Override
    public Mono<HistoryEventResponse> getHistoryEvent(Long eventId, Integer page) {
//        String url = SCRAPER_URL + "/?api_key=" + SCRAPER_API_KEY +
//                "&url=" + FOREX_URL + "/calendar/history/1-" + eventId + "?i=" + page;
        String url = FOREX_URL + "/calendar/history/1-" + eventId + "?i=" + page;
        return webClient
                .post()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(HistoryEventResponse.class)
                .onErrorResume(e -> {
                    log.error("Failed to fetch HistoryEventResponse", e);
                    return Mono.just(new HistoryEventResponse());
                });
    }

}
