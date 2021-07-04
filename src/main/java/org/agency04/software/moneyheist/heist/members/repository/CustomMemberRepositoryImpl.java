package org.agency04.software.moneyheist.heist.members.repository;

import org.agency04.software.moneyheist.heist.members.Member;
import org.agency04.software.moneyheist.heist.skills.Skill;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CustomMemberRepositoryImpl implements CustomMemberRepository{


    private final MemberRepository memberRepository;
    private final RelationshipInserter relationshipInserter;

    protected CustomMemberRepositoryImpl(MemberRepository memberRepository, RelationshipInserter relationshipInserter) {
        this.memberRepository = memberRepository;
        this.relationshipInserter = relationshipInserter;
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
