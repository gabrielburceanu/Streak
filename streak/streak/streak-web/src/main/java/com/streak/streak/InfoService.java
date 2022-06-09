package com.streak.streak;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;


@Component
public class InfoService implements InfoContributor {

    CallsCounterService callsCounterService;

    public InfoService(CallsCounterService callsCounterService) {
        this.callsCounterService = callsCounterService;
    }

    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("urls", callsCounterService.getCalls());
    }
}
