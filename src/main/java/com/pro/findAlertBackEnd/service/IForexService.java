package com.pro.findAlertBackEnd.service;

import com.pro.findAlertBackEnd.model.request.ApplySettingsRequest;
import com.pro.findAlertBackEnd.model.response.ApplySettingsResponse;
import com.pro.findAlertBackEnd.model.response.DetailsEventResponse;
import com.pro.findAlertBackEnd.model.response.HistoryEventResponse;
import com.pro.findAlertBackEnd.model.response.UpcomingInstrumentResponse;
import reactor.core.publisher.Mono;

public interface IForexService {
    Mono<DetailsEventResponse> getCalendarDetailsEvent(Long eventId);

    Mono<ApplySettingsResponse> getApplySettings(ApplySettingsRequest request);

    Mono<HistoryEventResponse> getHistoryEvent(Long eventId, Integer page);

    Mono<UpcomingInstrumentResponse> getDetailUpcomingInstrument();

}
