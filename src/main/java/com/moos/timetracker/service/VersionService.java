package com.moos.timetracker.service;

import com.moos.timetracker.consumer.rest.PartnerVersionConsumer;
import com.moos.timetracker.model.Version;
import com.moos.timetracker.technical.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class VersionService {

    @Autowired
    private PartnerVersionConsumer partnerVersionConsumer;

    @Timer
    public Version getVersion() throws InterruptedException {
        Version result = initVersionData();
        String partnerVersion = partnerVersionConsumer.getPartnerVersion();
        result = completeData(result, partnerVersion);
        return result;
    }

    @Timer
    private Version completeData(Version result, String partnerVersion) throws InterruptedException {
        result.setPartnerVersion(partnerVersion.replaceAll("-", "."));
        result.setReleaseDate(LocalDate.now());
        Thread.sleep(1000);
        return result;
    }

    @Timer
    private Version initVersionData() throws InterruptedException {
        Version result = new Version();
        result.setVersion("1.0.0");
        Thread.sleep(2000);
        return result;
    }

}
