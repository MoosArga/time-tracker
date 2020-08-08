package com.moos.timetracker.consumer.rest;

import com.moos.timetracker.technical.Timer;
import org.springframework.stereotype.Service;

@Service
public class PartnerVersionConsumer {

    @Timer
    public String getPartnerVersion() throws InterruptedException {
        Thread.sleep(3000);
        return "1-0-2";
    }

}
