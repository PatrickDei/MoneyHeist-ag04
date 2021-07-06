package org.agency04.software.moneyheist.repositories.member;

import org.agency04.software.moneyheist.entities.member.Member;
import org.agency04.software.moneyheist.entities.skill.Skill;

import java.util.List;


public interface CustomMemberRepository{

    Member saveMember(Member member, List<Skill> existingSkills);

}