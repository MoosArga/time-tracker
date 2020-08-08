package com.moos.timetracker.service;

import com.moos.timetracker.model.Version;
import com.moos.timetracker.technical.Timer;
import org.springframework.stereotype.Service;

@Service
public class VersionBuilder {

    @Timer
    public Version initVersionData() throws InterruptedException {
        Version result = new Version();
        result.setVersion("1.0.0");
        Thread.sleep(2000);
        return result;
    }

}
