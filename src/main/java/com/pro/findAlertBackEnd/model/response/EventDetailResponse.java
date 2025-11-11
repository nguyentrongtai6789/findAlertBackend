package com.pro.findAlertBackEnd.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class EventDetailResponse {
    private Data data;

    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    private static class Data {
        @JsonProperty("event_id")
        private Long eventId;
        private List<Spec> specs;
        private History history;
        @JsonProperty("show_linked")
        private boolean showLinked;
        @JsonProperty("linked_threads")
        private LinkedThreads linkedThreads;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    private static class Spec {
        private Integer order;
        private String title;
        private String html;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    private static class History {
        @JsonProperty("has_data_values")
        private boolean hasDataValues;
        private List<Event> events;
        @JsonProperty("has_more")
        private boolean hasMore;
        @JsonProperty("can_show_more")
        private boolean canShowMore;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    private static class Event {
        @JsonProperty("event_id")
        private Long eventId;
        private String impact;
        @JsonProperty("impact_class")
        private String impactClass;
        private String date;
        private String url;
        private String actual;
        private String previous;
        private String revision;
        private String forecast;
        private Integer actualBetterWorse;
        private Integer revisionBetterWorse;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    private static class LinkedThreads {
        private List<New> news;
        private List<?> threads;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    private static class New {
        private Long id;
        private String html;
    }

}
