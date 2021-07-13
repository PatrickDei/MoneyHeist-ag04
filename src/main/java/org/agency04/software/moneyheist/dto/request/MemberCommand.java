package org.agency04.software.moneyheist.dto.request;

import org.agency04.software.moneyheist.entity.member.MemberStatus;
import org.agency04.software.moneyheist.group.Group;
import org.agency04.software.moneyheist.service.MemberService;
import org.agency04.software.moneyheist.validation.StatusPattern;
import org.agency04.software.moneyheist.validation.Unique;
import org.agency04.software.moneyheist.validation.ValidMainSkill;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.stream.Collectors;


// members are validated upon entry into the API
@ValidMainSkill(groups = {Group.ValidationGroup.OnlySkillsRequired.class}, message = "The member must contain the defined main skill")
public class MemberCommand {

    @NotEmpty(groups = {Group.ValidationGroup.WholeObjectRequired.class})
    @NotBlank(groups = {Group.ValidationGroup.WholeObjectRequired.class})
    @Null(groups = {Group.ValidationGroup.OnlySkillsRequired.class})
    private final String name;

    @NotNull(groups = {Group.ValidationGroup.WholeObjectRequired.class})
    @Size(min = 1, max = 1,
            groups = {Group.ValidationGroup.WholeObjectRequired.class})
    @Pattern(message = "Sex can either be M or F", regexp = "M|F",
            groups = {Group.ValidationGroup.WholeObjectRequired.class})
    @Null(groups = {Group.ValidationGroup.OnlySkillsRequired.class})
    private final String sex;

    @NotNull(groups = {Group.ValidationGroup.WholeObjectRequired.class})
    @Email(groups = {Group.ValidationGroup.WholeObjectRequired.class})
    @Null(groups = {Group.ValidationGroup.OnlySkillsRequired.class})
    @Unique(service = MemberService.class,
            fieldName = "email",
            message = "Email already exists",
            groups = {Group.ValidationGroup.WholeObjectRequired.class})
    private final String email;

    @StatusPattern(anyOf = {MemberStatus.AVAILABLE, MemberStatus.EXPIRED, MemberStatus.INCARCERATED, MemberStatus.RETIRED},
            groups = {Group.ValidationGroup.WholeObjectRequired.class})
    @Null(groups = {Group.ValidationGroup.OnlySkillsRequired.class})
    private final MemberStatus status;


    // skill section
    @NotEmpty(groups = {Group.ValidationGroup.WholeObjectRequired.class},
            message = "There must be a list of skills")
    private final List<@Valid SkillCommand> skills;

    private final String mainSkill;

    @AssertTrue(groups = {Group.ValidationGroup.OnlySkillsRequired.class})
    private boolean isAtLeastOneFieldEntered(){
        return skills != null || !mainSkill.isEmpty();
    }

    @AssertTrue(message = "Make sure the list of skills contains the mainSkill!",
            groups = {Group.ValidationGroup.WholeObjectRequired.class})
    private boolean isValidMainSkill(){
        return mainSkill == null || skills == null || skills.stream().anyMatch( s -> s.getName().equalsIgnoreCase(mainSkill));
    }

    @AssertTrue(message = "There shouldn't be duplicated skill names",
            groups = {Group.ValidationGroup.OnlySkillsRequired.class,
                    Group.ValidationGroup.WholeObjectRequired.class})
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
