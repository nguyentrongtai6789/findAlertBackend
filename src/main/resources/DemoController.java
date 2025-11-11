package com.pro.findAlertBackEnd.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/api/demo")
@RequiredArgsConstructor
@Slf4j
public class DemoController {
    private final WebClient.Builder webClientBuilder;

    @GetMapping("/get-one")
    public ResponseEntity<?> getOne() {
        var json = webClientBuilder.build()
                .get()
                .uri("https://www.forexfactory.com/calendar/apply-settings/100000?navigation=1")
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

}
