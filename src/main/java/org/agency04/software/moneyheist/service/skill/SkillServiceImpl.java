package org.agency04.software.moneyheist.service.skill;

import org.agency04.software.moneyheist.entity.skill.Skill;
import org.agency04.software.moneyheist.repository.SkillRepository;
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
