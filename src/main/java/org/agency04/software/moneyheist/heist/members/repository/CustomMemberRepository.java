package org.agency04.software.moneyheist.heist.members.repository;

import org.agency04.software.moneyheist.heist.members.Member;
import org.agency04.software.moneyheist.heist.skills.Skill;

import java.util.List;


public interface CustomMemberRepository{

    Member saveMember(Member member, List<Skill> existingSkills);

}