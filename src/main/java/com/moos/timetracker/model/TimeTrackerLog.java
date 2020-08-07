package com.moos.timetracker.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
public class TimeTrackerLog {

    private LocalDateTime dateTime;
    private UUID shot;
    private String id;
    private String process;
    private String step;
    private Long duration;

}
