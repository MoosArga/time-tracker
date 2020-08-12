package com.moos.timetracker.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
public class TimeTrackerLog {

    private String logDateTime;
    private String logger;
    private String caller;
    private Long duration;

    public TimeTrackerLog() {
        this.logger = "TimeTrackerLog";
    }

}
