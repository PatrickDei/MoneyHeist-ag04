package org.agency04.software.moneyheist.services.heist;

import org.agency04.software.moneyheist.dto.EligibleHeistMembersDTO;
import org.agency04.software.moneyheist.dto.HeistDTO;
import org.agency04.software.moneyheist.entities.heist.Heist;
import org.agency04.software.moneyheist.entities.member.Member;
import org.agency04.software.moneyheist.entities.requirement.HeistRequirement;
import org.agency04.software.moneyheist.entities.skill.Skill;
import org.agency04.software.moneyheist.repositories.heist.HeistRepository;
import org.agency04.software.moneyheist.repositories.member.MemberRepository;
import org.agency04.software.moneyheist.transformation.Transformation;
import org.agency04.software.moneyheist.validation.requestEntities.HeistCommand;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HeistServiceImpl implements HeistService {

    @Autowired
    private Transformation transformation;
    @Autowired
    private HeistRepository heistRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Override
    public List<HeistDTO> findAll() {
        return heistRepository.findAll().stream().map(Transformation::heistToDTO).collect(Collectors.toList());
    }

    @Override
    public Integer saveHeist(HeistCommand heist) throws ParseException {
        return heistRepository.save(Transformation.commandToHeist(heist)).getId();
    }

    @Override
    public Integer updateHeistSkills(HeistCommand heist, Integer heistId){
        Heist heistToUpdate = this.heistRepository.findById(heistId).orElse(null);

        if(heistToUpdate == null)
            return null;

        heistToUpdate.updateRequirements(Transformation.commandToHeist(heist).getRequirements());

        return heistRepository.save(heistToUpdate).getId();
    }

    @Override
    public EligibleHeistMembersDTO getEligibleHeistMembers(Integer heistId){
        Heist heist = this.heistRepository.findById(heistId).orElse(null);

        if(heist == null)
            return null;

        List<Member> member = new ArrayList<>();
        for(HeistRequirement r : heist.getRequirements()) {
            Skill skill = r.getSkill();
            member.addAll(memberRepository.findEligibleMembers(skill.getName(), skill.getSkillLevel()));
        }

        return new EligibleHeistMembersDTO(Transformation.requirementsToDTO(heist.getRequirements()), Transformation.membersToEligibleMembersDTO(member));
    }

    @Override
    public boolean fieldValueExists(Object value, String fieldName) throws UnsupportedOperationException {
        Assert.assertNotNull(fieldName);

        if(!fieldName.equals("name"))
            throw new UnsupportedOperationException("Field name not supported");

        if(value == null)
            return false;

        return this.heistRepository.existsByName(value.toString());
    }
}
