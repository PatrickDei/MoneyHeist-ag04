package org.agency04.software.moneyheist.validation.requestEntities.heist;

import org.agency04.software.moneyheist.groups.OnlySkillsRequired;
import org.agency04.software.moneyheist.groups.WholeObjectRequired;
import org.agency04.software.moneyheist.services.heist.HeistService;
import org.agency04.software.moneyheist.validation.requestEntities.heist.requirement.HeistRequirementCommand;
import org.agency04.software.moneyheist.validation.requestEntities.skill.SkillCommand;
import org.agency04.software.moneyheist.validation.uniqueField.Unique;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

// heists are validated upon entry into the API but some extra checks need to be performed later (the ones that require data from db)
public class HeistCommand {

    @NotEmpty(groups = {WholeObjectRequired.class})
    @NotBlank(groups = {WholeObjectRequired.class})
    @Null(groups = {OnlySkillsRequired.class})
    @Unique(service = HeistService.class,
            fieldName = "name",
            message = "Heist name already exists",
            groups = WholeObjectRequired.class)
    private String name;

    @NotEmpty(groups = {WholeObjectRequired.class})
    @NotBlank(groups = {WholeObjectRequired.class})
    @Null(groups = {OnlySkillsRequired.class})
    private String location;

    @NotNull(groups = {WholeObjectRequired.class})
    @Null(groups = {OnlySkillsRequired.class})
    private Date startTime;

    @NotNull(groups = {WholeObjectRequired.class})
    @Future(groups = {WholeObjectRequired.class})
    @Null(groups = {OnlySkillsRequired.class})
    private Date endTime;

    @AssertTrue(message = "startTime must be before endTime",
            groups = {WholeObjectRequired.class})
    private boolean isValidTimeDifference(){
        return startTime.before(endTime);
    }

    @NotNull(message = "There must exist a list of requirements")
    private List<@Valid HeistRequirementCommand> skills;

    @AssertTrue(message = "There shouldn't be duplicated skill names and levels simultaneously")
    private boolean isValidSkillList(){
        Set<SkillCommand> requirement = skills.stream().map(HeistRequirementCommand::getSkill).collect(Collectors.toSet());
        return requirement.size() == skills.size();
    }

    public HeistCommand(String name, String location, Date startTime, Date endTime, List<@Valid HeistRequirementCommand> skills) {
        this.name = name;
        this.location = location;
        this.startTime = startTime;
        this.endTime = endTime;
        this.skills = skills;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public List<HeistRequirementCommand> getSkills() {
        return skills;
    }

    public void setSkills(List<HeistRequirementCommand> skills) {
        this.skills = skills;
    }
}