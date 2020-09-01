package com.challenge.controller;

import com.challenge.service.DateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/date")
public class DateController {

    @Autowired
    private DateServiceImpl dateService;

    @PostMapping("/set-day")
    public ResponseEntity<LocalDate> setDay(@RequestBody LocalDate date) {
        return ResponseEntity.ok().body(dateService.setActualDate(date));
    }

    @PostMapping("/next-day")
    public ResponseEntity<LocalDate> setDay() {
        return ResponseEntity.ok().body(dateService.nextDay());
    }

}
