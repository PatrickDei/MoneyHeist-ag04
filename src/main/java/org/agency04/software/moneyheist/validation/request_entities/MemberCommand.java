package org.agency04.software.moneyheist.validation.request_entities;

import org.agency04.software.moneyheist.entities.member.MemberStatus;
import org.agency04.software.moneyheist.groups_and_views.Group;
import org.agency04.software.moneyheist.repositories.member.MemberRepository;
import org.agency04.software.moneyheist.services.member.MemberService;
import org.agency04.software.moneyheist.validation.validators.enumeration.status.StatusPattern;
import org.agency04.software.moneyheist.validation.validators.unique_field.Unique;
import org.agency04.software.moneyheist.validation.validators.valid_main_skill.ValidMainSkill;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.stream.Collectors;


// members are validated upon entry into the API
@ValidMainSkill(groups = {Group.OnlySkillsRequired.class}, message = "The member must contain the defined main skill")
public class MemberCommand {
    @Autowired
    private MemberRepository memberRepository;

    @NotEmpty(groups = {Group.WholeObjectRequired.class})
    @NotBlank(groups = {Group.WholeObjectRequired.class})
    @Null(groups = {Group.OnlySkillsRequired.class})
    private String name;

    @NotNull(groups = {Group.WholeObjectRequired.class})
    @Size(min = 1, max = 1,
            groups = {Group.WholeObjectRequired.class})
    @Pattern(message = "Sex can either be M or F", regexp = "M|F",
            groups = {Group.WholeObjectRequired.class})
    @Null(groups = {Group.OnlySkillsRequired.class})
    private String sex;

    @NotNull(groups = {Group.WholeObjectRequired.class})
    @Email(groups = {Group.WholeObjectRequired.class})
    @Null(groups = {Group.OnlySkillsRequired.class})
    @Unique(service = MemberService.class,
            fieldName = "email",
            message = "Email already exists",
            groups = {Group.WholeObjectRequired.class})
    private String email;

    @StatusPattern(anyOf = {MemberStatus.AVAILABLE, MemberStatus.EXPIRED, MemberStatus.INCARCERATED, MemberStatus.RETIRED},
            groups = {Group.WholeObjectRequired.class})
    @Null(groups = {Group.OnlySkillsRequired.class})
    private MemberStatus status;


    // skill section
    @NotEmpty(groups = {Group.WholeObjectRequired.class},
            message = "There must be a list of skills")
    private List<@Valid SkillCommand> skills;

    private String mainSkill;

    @AssertTrue(groups = {Group.OnlySkillsRequired.class})
    private boolean isAtLeastOneFieldEntered(){
        return skills != null || !mainSkill.isEmpty();
    }

    @AssertTrue(message = "Make sure the list of skills contains the mainSkill!",
            groups = {Group.WholeObjectRequired.class})
    private boolean isValidMainSkill(){
        return mainSkill == null || skills == null || skills.stream().anyMatch( s -> s.getName().equalsIgnoreCase(mainSkill));
    }

    @AssertTrue(message = "There shouldn't be duplicated skill names",
            groups = {Group.OnlySkillsRequired.class,
                    Group.WholeObjectRequired.class})
    private boolean isSkillRepeated(){
        return skills == null || skills.stream().map(s -> (s.getName() != null) ? s.getName().toLowerCase() : null).collect(Collectors.toSet()).size() == skills.size();
    }

    public MemberCommand(String name, String sex, String email, MemberStatus memberStatus, List<@Valid SkillCommand> skills, String mainSkill) {
        this.name = name;
        this.sex = sex;
        this.email = email;
        this.status = memberStatus;
        this.skills = skills;
        this.mainSkill = mainSkill;
    }

    public String getName() {
        return name;
    }


    public String getSex() {
        return sex;
    }


    public String getEmail() {
        return email;
    }


    public MemberStatus getStatus() {
        return status;
    }

    public List<SkillCommand> getSkills() {
        return skills;
    }

    public String getMainSkill() {
        return mainSkill;
    }
}
