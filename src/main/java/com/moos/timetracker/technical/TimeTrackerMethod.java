package com.moos.timetracker.technical;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.moos.timetracker.model.TimeTrackerLog;
import net.logstash.logback.marker.Markers;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.json.Jackson2JsonEncoder;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Aspect
@Configuration
public class TimeTrackerMethod {

    private static Gson gson = new GsonBuilder().create();
    private static final Logger logger = LoggerFactory.getLogger("TimeTrackerLog");

    @Around("@annotation(com.moos.timetracker.technical.Timer)")
    public Object onMethod(ProceedingJoinPoint jp) throws Throwable {
        TimeTrackerLog tracker = new TimeTrackerLog();
        LocalDateTime startTime = LocalDateTime.now();
        tracker.setCaller(jp.getTarget().getClass().getName() + " - " + jp.getSignature().getName());
        Object methodResult = jp.proceed();
        LocalDateTime endTime = LocalDateTime.now();
        tracker.setLogDateTime(endTime.format(DateTimeFormatter.ISO_DATE_TIME));
        tracker.setDuration(Duration.between(startTime, endTime).toMillis());
//        logger.info(Markers.append("log", tracker), tracker.toString());
        System.out.println(gson.toJson(tracker));
        return methodResult;
    }

}
