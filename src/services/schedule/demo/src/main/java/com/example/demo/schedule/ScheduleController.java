package com.example.demo.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {
    private final ScheduleController scheduleController;

    @Autowired
    public ScheduleController(ScheduleController scheduleController){
        this.scheduleController = scheduleController;
    }
}
