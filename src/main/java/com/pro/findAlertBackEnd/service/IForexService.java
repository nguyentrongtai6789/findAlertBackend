package com.pro.findAlertBackEnd.service;

import com.pro.findAlertBackEnd.model.request.ApplySettingsRequest;
import com.pro.findAlertBackEnd.model.response.ApplySettingsResponse;
import com.pro.findAlertBackEnd.model.response.DetailsEventResponse;
import reactor.core.publisher.Mono;

public interface IForexService {
    Mono<DetailsEventResponse> getCalendarDetailsEvent(Long eventId);

    Mono<ApplySettingsResponse> getApplySettings(ApplySettingsRequest request);
}
