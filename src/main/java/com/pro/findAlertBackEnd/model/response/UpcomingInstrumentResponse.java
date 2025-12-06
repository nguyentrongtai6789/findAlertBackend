package com.pro.findAlertBackEnd.model.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UpcomingInstrumentResponse {
    @JsonProperty("has_more")
    private Boolean hasMore;
    List<Event> events;
    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    private static class Event {
        private Long id;
        private String title;
        private String dateline;
        @JsonProperty("in_time")
        private String inTime;
        @JsonProperty("time_mask")
        private String timeMask;
        @JsonProperty("impact_value")
        private String impactValue;
        @JsonProperty("impact_name")
        private String impactName;
        @JsonProperty("impact_icon_class")
        private String impactIconClass;
        private String forecast;
        private String previous;
        @JsonProperty("event_url")
        private String eventUrl;
        @JsonProperty("details_url")
        private String detailsUrl;
    }
}
