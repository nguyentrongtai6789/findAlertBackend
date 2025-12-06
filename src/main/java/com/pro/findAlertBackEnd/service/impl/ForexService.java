package com.pro.findAlertBackEnd.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pro.findAlertBackEnd.model.request.ApplySettingsRequest;
import com.pro.findAlertBackEnd.model.response.ApplySettingsResponse;
import com.pro.findAlertBackEnd.model.response.DetailsEventResponse;
import com.pro.findAlertBackEnd.model.response.HistoryEventResponse;
import com.pro.findAlertBackEnd.model.response.UpcomingInstrumentResponse;
import com.pro.findAlertBackEnd.service.IForexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
@Slf4j
public class ForexService implements IForexService {
    @Value("${forex.url}")
    private String FOREX_URL;

    @Cacheable(value = "detailsEvent", key = "#eventId")
    @Override
    public Mono<DetailsEventResponse> getCalendarDetailsEvent(Long eventId) {
        String url = FOREX_URL + "/calendar/details/1-" + eventId;
        return Mono.fromCallable(() -> {
            String[] cmd = {
                    "curl",
                    "-s",
                    "-L",
                    url,
                    "-H", "User-Agent: PostmanRuntime/7.49.1",
                    "-H", "Host: www.forexfactory.com",
                    "-H", "Cookie: __cf_bm=dGTo1wbZI7i4DavDYmEAWqu0pS1R57VJ4nGQdh2mXCc-1765009280-1.0.1.1-fy8CTOUFIQmjXNzzVRlMF0pHj__sp4MPBf8Vv3M7h8WS9koDY.HFDPmNI.xlOstSa29hHrU6ZS7ZDB1CggUpWZEK.wt.GqhWpQfY6mPC7nDXcqYH_Heqr1uO79kRKd3t; auth_user=3805da2f8c48d8002b042cdc848ef86a47ce9e7e13623d29833e68371e4a9e0d%3A7f28c890eec9a70cf1a3f66d6e33669678b39fd7b58969d18700c85ada122cb4; fflastactivity=0; fflastvisit=1765008586; ffsettingshash=49307cf48d8eb6bfb25e963ef7c0e045; fftab-history=calendar"
            };
            String json = execCurl(cmd);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, DetailsEventResponse.class);
        }).onErrorResume(e -> {
            log.error("Failed to fetch DetailsEventResponse via CURL:", e);
            return Mono.just(new DetailsEventResponse());
        });
    }


    @Cacheable(value = "applySettings", key = "#request")
    @Override
    public Mono<ApplySettingsResponse> getApplySettings(ApplySettingsRequest request) {
        String url = FOREX_URL + "/calendar/apply-settings/100000?navigation=1";
        return Mono.fromCallable(() -> {
            ObjectMapper mapper = new ObjectMapper();
            String jsonRequest = mapper.writeValueAsString(request);
            String[] cmd = {
                    "curl",
                    "-s",
                    "-L",
                    "-X", "POST",
                    url,
                    "-H", "User-Agent: PostmanRuntime/7.49.1",
                    "-H", "Host: www.forexfactory.com",
                    "-H", "Content-Type: application/json",
                    "-H", "Cookie: __cf_bm=dGTo1wbZI7i4DavDYmEAWqu0pS1R57VJ4nGQdh2mXCc-1765009280-1.0.1.1-fy8CTOUFIQmjXNzzVRlMF0pHj__sp4MPBf8Vv3M7h8WS9koDY.HFDPmNI.xlOstSa29hHrU6ZS7ZDB1CggUpWZEK.wt.GqhWpQfY6mPC7nDXcqYH_Heqr1uO79kRKd3t; auth_user=3805da2f8c48d8002b042cdc848ef86a47ce9e7e13623d29833e68371e4a9e0d%3A7f28c890eec9a70cf1a3f66d6e33669678b39fd7b58969d18700c85ada122cb4; fflastactivity=0; fflastvisit=1765008586; ffsettingshash=49307cf48d8eb6bfb25e963ef7c0e045; fftab-history=calendar",
                    "-d", jsonRequest
            };
            String jsonResponse = execCurl(cmd);
            return mapper.readValue(jsonResponse, ApplySettingsResponse.class);
        }).onErrorResume(e -> {
            log.error("Failed to fetch ApplySettingsResponse via CURL:", e);
            return Mono.just(new ApplySettingsResponse());
        });
    }

    @Cacheable(value = "historyEvent", key = "#eventId + '-' + #page")
    @Override
    public Mono<HistoryEventResponse> getHistoryEvent(Long eventId, Integer page) {
        String url = FOREX_URL + "/calendar/history/1-" + eventId + "?i=" + page;
        return Mono.fromCallable(() -> {
            String[] cmd = {
                    "curl",
                    "-s",
                    "-L",
                    "-X", "POST",
                    url,
                    "-H", "User-Agent: PostmanRuntime/7.49.1",
                    "-H", "Host: www.forexfactory.com",
                    "-H", "Content-Type: application/json",
                    "-H", "Cookie: __cf_bm=dGTo1wbZI7i4DavDYmEAWqu0pS1R57VJ4nGQdh2mXCc-1765009280-1.0.1.1-fy8CTOUFIQmjXNzzVRlMF0pHj__sp4MPBf8Vv3M7h8WS9koDY.HFDPmNI.xlOstSa29hHrU6ZS7ZDB1CggUpWZEK.wt.GqhWpQfY6mPC7nDXcqYH_Heqr1uO79kRKd3t; auth_user=3805da2f8c48d8002b042cdc848ef86a47ce9e7e13623d29833e68371e4a9e0d%3A7f28c890eec9a70cf1a3f66d6e33669678b39fd7b58969d18700c85ada122cb4; fflastactivity=0; fflastvisit=1765008586; ffsettingshash=49307cf48d8eb6bfb25e963ef7c0e045; fftab-history=calendar"
            };
            String json = execCurl(cmd);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, HistoryEventResponse.class);
        }).onErrorResume(e -> {
            log.error("Failed to fetch HistoryEventResponse via CURL:", e);
            return Mono.just(new HistoryEventResponse());
        });
    }

    @Override
    public Mono<UpcomingInstrumentResponse> getDetailUpcomingInstrument() {
        String url = FOREX_URL + "/calendar/upcoming/instrument/EURUSD?limit=8";
        return Mono.fromCallable(() -> {
            String[] cmd = {
                    "curl",
                    "-s",
                    "-L",
                    url,
                    "-H", "User-Agent: PostmanRuntime/7.49.1",
                    "-H", "Host: www.forexfactory.com",
                    "-H", "Cookie: __cf_bm=dGTo1wbZI7i4DavDYmEAWqu0pS1R57VJ4nGQdh2mXCc-1765009280-1.0.1.1-fy8CTOUFIQmjXNzzVRlMF0pHj__sp4MPBf8Vv3M7h8WS9koDY.HFDPmNI.xlOstSa29hHrU6ZS7ZDB1CggUpWZEK.wt.GqhWpQfY6mPC7nDXcqYH_Heqr1uO79kRKd3t; auth_user=3805da2f8c48d8002b042cdc848ef86a47ce9e7e13623d29833e68371e4a9e0d%3A7f28c890eec9a70cf1a3f66d6e33669678b39fd7b58969d18700c85ada122cb4; fflastactivity=0; fflastvisit=1765008586; ffsettingshash=49307cf48d8eb6bfb25e963ef7c0e045; fftab-history=calendar"
            };
            String json = execCurl(cmd);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, UpcomingInstrumentResponse.class);

        }).onErrorResume(e -> {
            log.error("Failed to fetch via CURL:", e);
            return Mono.just(new UpcomingInstrumentResponse());
        });
    }

    public String execCurl(String[] command) throws IOException {
        ProcessBuilder pb = new ProcessBuilder(command);
        pb.redirectErrorStream(true);
        Process process = pb.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder output = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line);
        }
        return output.toString();
    }

}
