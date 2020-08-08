package com.moos.timetracker.controller;

import com.moos.timetracker.model.Version;
import com.moos.timetracker.service.VersionService;
import com.moos.timetracker.technical.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class VersionController {

    @Autowired
    private VersionService versionService;

    @GetMapping("/version")
    @Timer
    public Version getVersion() throws InterruptedException {
        Version result = versionService.getVersion();
        return result;
    }
}
