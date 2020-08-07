package com.moos.timetracker.controller;

import com.moos.timetracker.model.StepEnum;
import com.moos.timetracker.model.Version;
import com.moos.timetracker.service.TimeTrackerService;
import com.moos.timetracker.service.VersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class VersionController {

    @Autowired
    private VersionService versionService;

    @Autowired
    private TimeTrackerService timeTrackerService;

    @GetMapping("/version")
    public Version getVersion() throws InterruptedException {
        timeTrackerService.startRecord("get-version");
        Version result = versionService.getVersion();
        timeTrackerService.endRecord("get-version");
        return result;
    }
}
