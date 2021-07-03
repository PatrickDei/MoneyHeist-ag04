package org.agency04.software.moneyheist.heist.members;

import org.agency04.software.moneyheist.heist.skills.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;
    private final SkillRepository skillRepository;
    private final SkillService skillService;

    public MemberServiceImpl(MemberRepository memberRepository, SkillRepository skillRepository, SkillService skillService) {
        this.memberRepository = memberRepository;
        this.skillRepository = skillRepository;
        this.skillService = skillService;
    }

    private Member CommandToMember(MemberCommand memberCommand){
        return new Member(
                memberCommand.getName(),
                memberCommand.getSex(),
                memberCommand.getEmail(),
                memberCommand.getSkills().stream().map(skillService::CommandToSkill).collect(Collectors.toSet()),
                memberCommand.getMainSkill(),
                memberCommand.getStatus()
        );
    }

    private Skill CommandToSkill(SkillCommand skillCommand){
        return new Skill(skillCommand.getName(), skillCommand.getLevel().length());
    }

    private MemberDTO MemberToDTO(Member member){
        return new MemberDTO(member.getName(), member.getSkills().stream().map(this::SkillToDTO).collect(Collectors.toSet()), member.getMainSkill());
    }

    private SkillDTO SkillToDTO(Skill skill){
        return new SkillDTO(skill.getName(), this.SkillLevelToString(skill.getSkillLevel()));
    }

    private String SkillLevelToString(Integer count){
        return "*".repeat(count);
    }

    public Integer saveMember(MemberCommand member){
        return this.memberRepository.save(this.CommandToMember(member)).getId();
    }

    public List<MemberDTO> findAll(){
        return this.memberRepository.findAll().stream().map(this::MemberToDTO).collect(Collectors.toList());
    }
}

interface MemberService{
    Integer saveMember(MemberCommand member);

    List<MemberDTO> findAll();
}