package org.agency04.software.moneyheist.members.repository;

import org.agency04.software.moneyheist.members.Member;
import org.agency04.software.moneyheist.skills.Skill;

import java.util.List;


public interface CustomMemberRepository{

    Member saveMember(Member member, List<Skill> existingSkills);

}