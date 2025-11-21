package com.pro.findAlertBackEnd.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class HistoryEventResponse {
    private HistoryEventsData data;

    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    private static class HistoryEventsData {
        @JsonProperty("history")
        private DetailsEventResponse.History history;
    }
}
