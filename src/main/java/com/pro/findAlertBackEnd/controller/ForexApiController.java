package com.pro.findAlertBackEnd.controller;

import com.pro.findAlertBackEnd.service.IForexService;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/forex")
public class ForexApiController {
    private final IForexService forexService;

    public ForexApiController(IForexService forexService) {
        this.forexService = forexService;
    }

    @GetMapping("/calendar/details-event")
    public ResponseEntity<?> getCalendarDetailsEvent(@RequestParam @NonNull Long eventId) {
        var body = forexService.getCalendarDetailsEvent(eventId);
        return ResponseEntity.ok(body);
    }
}
