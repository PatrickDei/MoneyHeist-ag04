package org.agency04.software.moneyheist.dto.request;

import org.agency04.software.moneyheist.group.Group;
import org.agency04.software.moneyheist.service.HeistService;
import org.agency04.software.moneyheist.validation.Unique;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

// heists are validated upon entry into the API
public class HeistCommand {

    @NotEmpty(groups = {Group.ValidationGroup.WholeObjectRequired.class})
    @NotBlank(groups = {Group.ValidationGroup.WholeObjectRequired.class})
    @Null(groups = {Group.ValidationGroup.OnlySkillsRequired.class})
    @Unique(service = HeistService.class,
            fieldName = "name",
            message = "Heist name already exists",
            groups = Group.ValidationGroup.WholeObjectRequired.class)
    private final String name;

    @NotEmpty(groups = {Group.ValidationGroup.WholeObjectRequired.class})
    @NotBlank(groups = {Group.ValidationGroup.WholeObjectRequired.class})
    @Null(groups = {Group.ValidationGroup.OnlySkillsRequired.class})
    private final String location;

    @NotNull(groups = {Group.ValidationGroup.WholeObjectRequired.class})
    @Null(groups = {Group.ValidationGroup.OnlySkillsRequired.class})
    private final Date startTime;

    @NotNull(groups = {Group.ValidationGroup.WholeObjectRequired.class})
    @Future(groups = {Group.ValidationGroup.WholeObjectRequired.class})
    @Null(groups = {Group.ValidationGroup.OnlySkillsRequired.class})
    private final Date endTime;

    @NotNull(message = "There must exist a list of requirements",
            groups = {Group.ValidationGroup.WholeObjectRequired.class, Group.ValidationGroup.OnlySkillsRequired.class})
    private final List<@Valid HeistRequirementCommand> skills;


    @AssertTrue(message = "startTime must be before endTime",
            groups = {Group.ValidationGroup.WholeObjectRequired.class})
    public boolean isValidTimeDifference(){
        return startTime.before(endTime);
    }

    @AssertTrue(message = "There shouldn't be duplicated skill names and levels simultaneously",
            groups = {Group.ValidationGroup.WholeObjectRequired.class, Group.ValidationGroup.OnlySkillsRequired.class})
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

    public String getLocation() {
        return location;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public List<HeistRequirementCommand> getSkills() {
        return skills;
    }
}