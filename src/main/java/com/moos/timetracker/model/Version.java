package com.moos.timetracker.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class Version {

    private String version;
    private String partnerVersion;
    private LocalDate releaseDate;

}
