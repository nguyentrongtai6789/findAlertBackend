package com.pro.findAlertBackEnd.controller;

import com.pro.findAlertBackEnd.model.request.ApplySettingsRequest;
import com.pro.findAlertBackEnd.service.IForexService;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/calendar/apply-settings")
    public ResponseEntity<?> getApplySettings(@ModelAttribute ApplySettingsRequest request) {
        var body = forexService.getApplySettings(request);
        return ResponseEntity.ok(body);
    }

    @GetMapping("/calendar/history-event")
    public ResponseEntity<?> getHistoryEvent(@RequestParam @NonNull Long eventId, @RequestParam @NonNull Integer page) {
        var body = forexService.getHistoryEvent(eventId, page);
        return ResponseEntity.ok(body);
    }

    @GetMapping("/calendar/upcoming/instrument")
    public ResponseEntity<?> getUpcomingInstrument() {
        var body = forexService.getDetailUpcomingInstrument();
        return ResponseEntity.ok(body);
    }
}
