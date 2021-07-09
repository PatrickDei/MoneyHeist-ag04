package org.agency04.software.moneyheist.services.skill;

import org.agency04.software.moneyheist.entities.skill.Skill;
import org.agency04.software.moneyheist.repositories.skill.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    private SkillRepository skillRepository;

    @Override
    public List<Skill> findAll(){
        return skillRepository.findAll();
    }
}
