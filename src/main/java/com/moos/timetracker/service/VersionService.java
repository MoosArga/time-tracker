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

    @Autowired
    private VersionBuilder versionBuilder;

    @Autowired
    private VersionProcessor versionProcessor;

    @Timer
    public Version getVersion() throws InterruptedException {
        Version result = versionBuilder.initVersionData();
        String partnerVersion = partnerVersionConsumer.getPartnerVersion();
        result = versionProcessor.completeData(result, partnerVersion);
        return result;
    }

}
