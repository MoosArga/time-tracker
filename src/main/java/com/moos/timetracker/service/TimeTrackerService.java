package com.moos.timetracker.service;

import com.moos.timetracker.model.StepEnum;
import com.moos.timetracker.model.TimeTrackerLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static net.logstash.logback.marker.Markers.append;

@Service
public class TimeTrackerService {

    private static final Logger logger = LoggerFactory.getLogger("TimeTrackerLog");
    private static Map<String, TimeTrackerLog> repository = new HashMap<>();

    public void startRecord(String process, String id) {
        TimeTrackerLog log = new TimeTrackerLog();
        log.setProcess(process);
        log.setDateTime(LocalDateTime.now());
        log.setShot(UUID.randomUUID());
        log.setId(id);
        repository.put(id, log);
    }

    public void startRecord(String process) {
        startRecord(process, process);
    }

    public void closeStep(String id, String step) {
        TimeTrackerLog log = repository.get(id);
        LocalDateTime logDateTime = LocalDateTime.now();
        log.setStep(step);
        log.setDuration(Duration.between(log.getDateTime(), logDateTime).toMillis());
        log.setDateTime(logDateTime);
        logger.info(append("log", log), log.toString());
    }

    public void endRecord(String id) {
        closeStep(id, StepEnum.API_RESPONSE.name());
        repository.remove(id);
    }

}
