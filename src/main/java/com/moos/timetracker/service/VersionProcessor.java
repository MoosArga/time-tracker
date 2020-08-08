package com.moos.timetracker.service;

import com.moos.timetracker.model.Version;
import com.moos.timetracker.technical.Timer;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class VersionProcessor {

    @Timer
    public Version completeData(Version result, String partnerVersion) throws InterruptedException {
        result.setPartnerVersion(partnerVersion.replaceAll("-", "."));
        result.setReleaseDate(LocalDate.now());
        Thread.sleep(1000);
        return result;
    }

}
