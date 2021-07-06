package org.agency04.software.moneyheist.validation.member;

import org.agency04.software.moneyheist.groups.OnlySkillsRequired;
import org.agency04.software.moneyheist.groups.WholeObjectRequired;
import org.agency04.software.moneyheist.entities.member.Status;
import org.agency04.software.moneyheist.services.member.MemberService;
import org.agency04.software.moneyheist.validation.skill.SkillCommand;
import org.agency04.software.moneyheist.validation.uniquefield.Unique;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.stream.Collectors;


// members are validated upon entry into the API but some extra checks need to be performed later (the ones that require data from db)
public class MemberCommand {

    @NotEmpty(groups = {WholeObjectRequired.class})
    @NotBlank(groups = {WholeObjectRequired.class})
    @Null(groups = {OnlySkillsRequired.class})
    private String name;

    @NotNull(groups = {WholeObjectRequired.class})
    @Size(min = 1, max = 1,
            groups = {WholeObjectRequired.class})
    @Pattern(message = "Sex can either be M or F", regexp = "M|F",
            groups = {WholeObjectRequired.class})
    @Null(groups = {OnlySkillsRequired.class})
    private String sex;

    @NotNull(groups = {WholeObjectRequired.class})
    @Email(groups = {WholeObjectRequired.class})
    @Null(groups = {OnlySkillsRequired.class})
    @Unique(service = MemberService.class, fieldName = "email", message = "Email already exists")
    private String email;

    @StatusPattern(anyOf = {Status.AVAILABLE, Status.EXPIRED, Status.INCARCERATED, Status.RETIRED},
            groups = {WholeObjectRequired.class})
    @Null(groups = {OnlySkillsRequired.class})
    private Status status;


    // skill section
    @NotEmpty(groups = {WholeObjectRequired.class},
            message = "There must be a list of skills")
    private List<@Valid SkillCommand> skills;

    private String mainSkill;


    @AssertTrue(groups = {OnlySkillsRequired.class})
    private boolean isAtLeastOneFieldEntered(){
        return skills != null || !mainSkill.isEmpty();
    }

    @AssertTrue(message = "Make sure the list of skills contains the mainSkill!",
            groups = {WholeObjectRequired.class})
    private boolean isValidMainSkill(){
        return mainSkill == null || skills.stream().anyMatch( s -> s.getName().equals(mainSkill));
    }

    @AssertTrue(message = "There shouldn't be duplicated skill names",
            groups = {OnlySkillsRequired.class, WholeObjectRequired.class})
    private boolean isSkillRepeated(){
        return skills == null || skills.stream().map(SkillCommand::getName).collect(Collectors.toSet()).size() == skills.size();
    }

    public MemberCommand(String name, String sex, String email, Status status, List<@Valid SkillCommand> skills, String mainSkill) {
        this.name = name;
        this.sex = sex;
        this.email = email;
        this.status = status;
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


    public Status getStatus() {
        return status;
    }

    public List<SkillCommand> getSkills() {
        return skills;
    }

    public String getMainSkill() {
        return mainSkill;
    }
}
