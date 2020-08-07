package com.moos.timetracker.service;

import com.moos.timetracker.consumer.rest.PartnerVersionConsumer;
import com.moos.timetracker.model.StepEnum;
import com.moos.timetracker.model.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class VersionService {

    @Autowired
    private PartnerVersionConsumer partnerVersionConsumer;

    @Autowired
    private TimeTrackerService timeTrackerService;

    public Version getVersion() throws InterruptedException {
        Version result = new Version();
        result.setVersion("1.0.0");
        Thread.sleep(2000);
        timeTrackerService.closeStep("get-version", StepEnum.API_REQUEST.name());
        String partnerVersion = partnerVersionConsumer.getPartnerVersion();
        timeTrackerService.closeStep("get-version", StepEnum.PROVIDER_CALL.name());
        Thread.sleep(500);
        result.setPartnerVersion(partnerVersion.replaceAll("-", "."));
        timeTrackerService.closeStep("get-version", StepEnum.PROCESS_DATA.name());
        result.setReleaseDate(LocalDate.now());
        Thread.sleep(1000);
        return result;
    }

}
