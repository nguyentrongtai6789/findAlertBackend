package com.pro.findAlertBackEnd.model.request;

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
public class ApplySettingsRequest {
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
