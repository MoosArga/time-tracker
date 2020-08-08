package com.moos.timetracker.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TimeTrackerLog {

    private String caller;
    private Long duration;

}
