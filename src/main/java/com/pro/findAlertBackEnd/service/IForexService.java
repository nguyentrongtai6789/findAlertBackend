package com.pro.findAlertBackEnd.service;

import com.pro.findAlertBackEnd.model.response.DetailsEventResponse;
import reactor.core.publisher.Mono;

public interface IForexService {
    Mono<DetailsEventResponse> getCalendarDetailsEvent(Long eventId);
//    Mono<DetailsEventResponse> getCalendarDetailsEvent(Long eventId);
}
