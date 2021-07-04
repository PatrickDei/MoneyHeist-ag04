package org.agency04.software.moneyheist.heist.members.validation;

import org.agency04.software.moneyheist.heist.members.Status;
import org.agency04.software.moneyheist.heist.members.validation.StatusPattern;
import org.agency04.software.moneyheist.heist.skills.SkillCommand;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MemberCommand {

    public interface WholeMemberRequired{};
    public interface OnlySkillsRequired{};

    @NotEmpty(groups = {WholeMemberRequired.class})
    @NotBlank(groups = {WholeMemberRequired.class})
    private String name;

    @NotNull(groups = {WholeMemberRequired.class})
    @Size(min = 1, max = 1,
            groups = {WholeMemberRequired.class})
    @Pattern(message = "Sex can either be M or F", regexp = "M|F",
            groups = {WholeMemberRequired.class})
    private String sex;

    @NotNull(groups = {WholeMemberRequired.class})
    @Email(groups = {WholeMemberRequired.class})
    private String email;

    @StatusPattern(anyOf = {Status.AVAILABLE, Status.EXPIRED, Status.INCARCERATED, Status.RETIRED},
            groups = {WholeMemberRequired.class})
    private Status status;


    // skill section
    @AssertTrue(groups = {OnlySkillsRequired.class})
    private boolean isAtLeastOneFieldEntered(){
        return !(skills == null) || !mainSkill.isEmpty();
    }

    @AssertTrue(message = "Make sure the list of skills contains the mainSkill!",
            groups = {WholeMemberRequired.class})
    private boolean isValidMainSkill(){
        return mainSkill == null || skills.stream().anyMatch( s -> s.getName().equals(mainSkill));
    }

    @AssertTrue(message = "There shouldn't be duplicated skill names",
            groups = {OnlySkillsRequired.class, WholeMemberRequired.class})
    private boolean isSkillRepeated(){
        if(!(skills == null)) {
            Set<String> setOfSkills = skills.stream().map(SkillCommand::getName).collect(Collectors.toSet());
            return setOfSkills.size() == skills.size();
        }
        return true;
    }

    @NotEmpty(groups = {WholeMemberRequired.class},
            message = "There must be a list of skills")
    private List<@Valid SkillCommand> skills;

    private String mainSkill;

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
