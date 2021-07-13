package org.agency04.software.moneyheist.configuration;

import org.agency04.software.moneyheist.entity.heist.Heist;
import org.agency04.software.moneyheist.entity.heist.HeistStatus;
import org.agency04.software.moneyheist.service.HeistService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.List;

@Configuration
@EnableScheduling
public class HeistDirector {

    private final HeistService heistService;

    public HeistDirector(HeistService heistService) {
        this.heistService = heistService;
    }

    @Scheduled(fixedDelay = 5000)
    public void startHeists(){
        List<Heist> heists = heistService.findHeistsByOutcomeIsNull();

        for(Heist h : heists){
            if(h.getStartTime().before(new Date()) && !h.getStatus().equals(HeistStatus.IN_PROGRESS))
                heistService.startHeist(h.getId());
        }
    }

    @Scheduled(fixedDelay = 5000)
    public void endHeists(){
        List<Heist> heists = heistService.findHeistsByOutcomeIsNull();

        for(Heist h : heists){
            if(h.getEndTime().before(new Date()))
                heistService.finishHeist(h.getId());
        }
    }
}
