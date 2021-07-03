package org.agency04.software.moneyheist.heist.skills;

import org.springframework.stereotype.Service;

@Service
public class SkillServiceImpl implements SkillService{
    @Override
    public Skill CommandToSkill(SkillCommand skillCommand) {
        return new Skill(skillCommand.getName(), skillCommand.getLevel().length());
    }
}

