package org.agency04.software.moneyheist.configuration;

import org.agency04.software.moneyheist.entities.heist.Heist;
import org.agency04.software.moneyheist.entities.heist.HeistStatus;
import org.agency04.software.moneyheist.repositories.HeistRepository;
import org.agency04.software.moneyheist.services.heist.HeistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.List;

@Configuration
@EnableScheduling
public class HeistDirector {

    @Autowired
    private HeistRepository heistRepository;
    @Autowired
    private HeistService heistService;

    @Scheduled(fixedDelay = 5000)
    public void startHeists(){
        List<Heist> heists = heistRepository.findByOutcomeIsNull();

        for(Heist h : heists){
            if(h.getStartTime().before(new Date()) && !h.getStatus().equals(HeistStatus.IN_PROGRESS))
                heistService.startHeist(h.getId());
        }
    }

    @Scheduled(fixedDelay = 5000)
    public void endHeists(){
        List<Heist> heists = heistRepository.findByOutcomeIsNull();

        for(Heist h : heists){
            if(h.getEndTime().before(new Date()))
                heistService.finishHeist(h.getId());
        }
    }
}
