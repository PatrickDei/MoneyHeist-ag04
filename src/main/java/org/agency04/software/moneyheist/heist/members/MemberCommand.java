package org.agency04.software.moneyheist.heist.members;

import org.agency04.software.moneyheist.heist.skills.SkillCommand;
import org.agency04.software.moneyheist.validation.enumeration.StatusPattern;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

public class MemberCommand {

    @AssertTrue(message = "Make sure the list of skills contains the mainSkill!")
    private boolean isOk(){
        return skills.stream().anyMatch( s -> s.getName().equals(mainSkill));
    }

    @NotEmpty
    @NotBlank
    private String name;

    @NotNull
    @Size(min = 1, max = 1)
    @Pattern(message = "Sex can either be M or F", regexp = "M|F")
    private String sex;

    @NotNull
    @Email
    private String email;

    private List<@Valid SkillCommand> skills;

    @NotNull
    @NotEmpty
    private String mainSkill;

    @StatusPattern(anyOf = {Status.AVAILABLE, Status.EXPIRED, Status.INCARCERATED, Status.RETIRED})
    private Status status;

    public MemberCommand(String name, String sex, String email, List<@Valid SkillCommand> skills, String mainSkill, Status status) {
        this.name = name;
        this.sex = sex;
        this.email = email;
        this.skills = skills;
        this.mainSkill = mainSkill;
        this.status = status;
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


    public List<SkillCommand> getSkills() {
        return skills;
    }


    public String getMainSkill() {
        return mainSkill;
    }


    public Status getStatus() {
        return status;
    }

}
