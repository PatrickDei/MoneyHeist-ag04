package org.agency04.software.moneyheist.repositories.member;

import org.agency04.software.moneyheist.entities.member.Member;
import org.agency04.software.moneyheist.entities.skill.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CustomMemberRepositoryImpl implements CustomMemberRepository{

    @Autowired
    private MemberSkillRelationshipInserter relationshipInserter;
    private final MemberRepository memberRepository;

    protected CustomMemberRepositoryImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    @Transactional
    public Member saveMember(Member member, List<Skill> existingSkills){

        // remove those skills from the object
        member.setSkills(
                member.getSkills().stream().filter(skill -> !existingSkills.contains(skill)).collect(Collectors.toSet())
        );

        try {
            // add the object without problematic skills
            Member addedMember = memberRepository.save(member);

            // now manually connect object and removed skills
            relationshipInserter.AddSkillsForMember(addedMember, existingSkills);

            return addedMember;
        }
        catch(Exception e){
            return null;
        }
    }
}
