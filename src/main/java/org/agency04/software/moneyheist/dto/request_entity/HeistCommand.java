package org.agency04.software.moneyheist.dto.request_entity;

import org.agency04.software.moneyheist.groups_and_views.Group;
import org.agency04.software.moneyheist.service.heist.HeistService;
import org.agency04.software.moneyheist.validation.unique_field.Unique;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

// heists are validated upon entry into the API but some extra checks need to be performed later (the ones that require data from db)
public class HeistCommand {

    @NotEmpty(groups = {Group.WholeObjectRequired.class})
    @NotBlank(groups = {Group.WholeObjectRequired.class})
    @Null(groups = {Group.OnlySkillsRequired.class})
    @Unique(service = HeistService.class,
            fieldName = "name",
            message = "Heist name already exists",
            groups = Group.WholeObjectRequired.class)
    private String name;

    @NotEmpty(groups = {Group.WholeObjectRequired.class})
    @NotBlank(groups = {Group.WholeObjectRequired.class})
    @Null(groups = {Group.OnlySkillsRequired.class})
    private String location;

    @NotNull(groups = {Group.WholeObjectRequired.class})
    @Null(groups = {Group.OnlySkillsRequired.class})
    private Date startTime;

    @NotNull(groups = {Group.WholeObjectRequired.class})
    @Future(groups = {Group.WholeObjectRequired.class})
    @Null(groups = {Group.OnlySkillsRequired.class})
    private Date endTime;

    @AssertTrue(message = "startTime must be before endTime",
            groups = {Group.WholeObjectRequired.class})
    public boolean isValidTimeDifference(){
        return startTime.before(endTime);
    }

    @NotNull(message = "There must exist a list of requirements",
            groups = {Group.WholeObjectRequired.class, Group.OnlySkillsRequired.class})
    private List<@Valid HeistRequirementCommand> skills;

    @AssertTrue(message = "There shouldn't be duplicated skill names and levels simultaneously",
            groups = {Group.WholeObjectRequired.class, Group.OnlySkillsRequired.class})
    public boolean isValidSkillList(){
        return skills != null && skills.stream().map(HeistRequirementCommand::getSkill).collect(Collectors.toSet()).size() == skills.size();
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