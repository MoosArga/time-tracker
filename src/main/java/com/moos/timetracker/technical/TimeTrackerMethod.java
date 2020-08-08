package com.moos.timetracker.technical;

import com.moos.timetracker.model.TimeTrackerLog;
import net.logstash.logback.marker.Markers;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;

import java.time.Duration;
import java.time.LocalDateTime;

@Aspect
@Configuration
public class TimeTrackerMethod {

    private static final Logger logger = LoggerFactory.getLogger("TimeTrackerLog");

    @Around("@annotation(com.moos.timetracker.technical.Timer)")
    public Object onMethod(ProceedingJoinPoint jp) throws Throwable {
        TimeTrackerLog tracker = new TimeTrackerLog();
        LocalDateTime startTime = LocalDateTime.now();
        tracker.setCaller(jp.getTarget().getClass().getName() + " - " + jp.getSignature().getName());
        Object methodResult = jp.proceed();
        tracker.setDuration(Duration.between(startTime, LocalDateTime.now()).toMillis());
        logger.info(Markers.append("log", tracker), tracker.toString());
        return methodResult;
    }

}
