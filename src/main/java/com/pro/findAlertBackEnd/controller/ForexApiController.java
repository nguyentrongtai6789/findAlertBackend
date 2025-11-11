package com.pro.findAlertBackEnd.controller;

import com.pro.findAlertBackEnd.service.ForexService;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/forex")
public class ForexApiController {
    private final ForexService forexService;

    public ForexApiController(ForexService forexService) {
        this.forexService = forexService;
    }

    @GetMapping("/calendar/details-event")
    public ResponseEntity<?> getOne(@RequestParam @NonNull Long eventId) {
        var body = forexService.detailsEvent(eventId);
        return ResponseEntity.ok(body);
    }
}
