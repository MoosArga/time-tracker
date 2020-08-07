package com.moos.timetracker.consumer.rest;

import org.springframework.stereotype.Service;

@Service
public class PartnerVersionConsumer {

    public String getPartnerVersion() throws InterruptedException {
        Thread.sleep(3000);
        return "1-0-2";
    }

}
