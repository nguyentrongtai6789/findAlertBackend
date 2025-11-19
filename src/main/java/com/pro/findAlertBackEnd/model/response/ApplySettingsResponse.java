package com.pro.findAlertBackEnd.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ApplySettingsResponse {
    private Settings settings;
    private Navigation navigation;
    private List<Day> days;
    private Boolean more;
    private Boolean append;
    private String upnext;
    private String mini;

    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    private static class Settings {
        @JsonProperty("begin_date")
        private String beginDate;
        @JsonProperty("end_date")
        private String endDate;
        @JsonProperty("default_view")
        private String defaultView;
        private List<Integer> impacts;
        @JsonProperty("event_types")
        private List<Integer> eventTypes;
        private List<Integer> currencies;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    private static class Navigation {
        private NavigationEntry last;
        private NavigationEntry current;
        private NavigationEntry next;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    private static class NavigationEntry {
        private String url;
        private String title;
        private NavigationDates dates;
        private Boolean valid;
        private NavigationDistance distance;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    private static class NavigationDates {
        private String start;
        private String end;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    private static class NavigationDistance {
        private Integer start;
        private Integer end;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    private static class Day {
        private String date;
        private Long dateline;
        private String add;
        private List<Event> events;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    private static class Event {
        private Long id;
        private String name;
        private String url;
        private String currency;
        private String date;
        private String timeLabel;
        private Long dateline;
        private String impactName;
        private String impactTitle;
        private Long ebaseId;
        private String prefixedName;
        private String trimmedPrefixedName;
        private String soloTitle;
        private String soloTitleFull;
        private String soloTitleShort;
        private String notice;
        private Boolean hasLinkedThreads;
        private Boolean hasNotice;
        private Boolean hasDataValues;
        private Boolean hasGraph;
        private Boolean checkedIn;
        private Boolean isMasterList;
        private Boolean firstInDay;
        private Boolean showGridLine;
        private Boolean greyed;
        private Boolean upNext;
        private String releaser;
        private String checker;
        private String impactClass;
        private Boolean timeMasked;
        private Integer hideHistory;
        private Integer hideSoloPage;
        private String actual;
        private String previous;
        private String revision;
        private String forecast;
        private Boolean leaked;
        private Integer actualBetterWorse;
        private Integer revisionBetterWorse;
        private Boolean isSubscribable;
        private Boolean isSubscribed;
        private Boolean showDetails;
        private Boolean showGraph;
        private Boolean enableDetailComponent;
        private Boolean enableExpandComponent;
        private Boolean enableActualComponent;
        private Boolean showExpanded;
        private Long siteId;
        private String editUrl;
        private String soloUrl;
    }
}
