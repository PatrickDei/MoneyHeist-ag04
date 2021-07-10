package org.agency04.software.moneyheist.configuration;

import org.agency04.software.moneyheist.entities.heist.Heist;
import org.agency04.software.moneyheist.entities.heist.HeistStatus;
import org.agency04.software.moneyheist.repositories.heist.HeistRepository;
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

    @Scheduled(fixedDelay = 5000)
    public void startHeists(){
        List<Heist> heists = heistRepository.findAll();
        for(Heist h : heists){
            if(h.getStartTime().before(new Date())) {
                h.setStatus(HeistStatus.IN_PROGRESS);
                heistRepository.save(h);
            }
        }
    }

    @Scheduled(fixedDelay = 5000)
    public void endHeists(){
        List<Heist> heists = heistRepository.findAll();
        for(Heist h : heists){
            if(h.getStartTime().before(new Date())) {
                h.setStatus(HeistStatus.FINISHED);
                heistRepository.save(h);
            }
        }
    }
}
