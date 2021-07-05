package org.agency04.software.moneyheist.skills.exceptions;

import org.agency04.software.moneyheist.skills.Skill;

import java.util.List;

// this exception exists to stop jpa from entering duplicates in db
public class SkillAlreadyExistsException extends RuntimeException{
    private List<Skill> existingSkills;

    public SkillAlreadyExistsException(List<Skill> existingSkills) {
        this.existingSkills = existingSkills;
    }

    public SkillAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public List<Skill> getExistingSkills() {
        return existingSkills;
    }
}
