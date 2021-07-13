package org.agency04.software.moneyheist.dto.response;

import com.fasterxml.jackson.annotation.JsonView;
import org.agency04.software.moneyheist.entity.heist.HeistOutcome;
import org.agency04.software.moneyheist.entity.heist.HeistStatus;
import org.agency04.software.moneyheist.group.Group;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class HeistDTO {

    @JsonView(Group.JsonViewGroup.BasicHeistInfo.class)
    private String name;

    @JsonView(Group.JsonViewGroup.BasicHeistInfo.class)
    private String location;

    @JsonView(Group.JsonViewGroup.BasicHeistInfo.class)
    private Date startTime;

    @JsonView(Group.JsonViewGroup.BasicHeistInfo.class)
    private Date endTime;

    @JsonView({Group.JsonViewGroup.BasicHeistInfo.class, Group.JsonViewGroup.EligibleMembers.class})
    private List<HeistRequirementDTO> skills;

    @JsonView(Group.JsonViewGroup.EligibleMembers.class)
    private Set<MemberDTO> members;

    @JsonView({Group.JsonViewGroup.BasicHeistInfo.class, Group.JsonViewGroup.HeistStatus.class})
    private HeistStatus status;

    @JsonView(Group.JsonViewGroup.HeistOutcome.class)
    private HeistOutcome outcome;

    public HeistDTO(String name, String location, Date startTime, Date endTime, List<HeistRequirementDTO> skills, Set<MemberDTO> members, HeistStatus status, HeistOutcome outcome) {
        this.name = name;
        this.location = location;
        this.startTime = startTime;
        this.endTime = endTime;
        this.skills = skills;
        this.members = members;
        this.status = status;
        this.outcome = outcome;
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

    public List<HeistRequirementDTO> getSkills() {
        return skills;
    }

    public void setSkills(List<HeistRequirementDTO> skills) {
        this.skills = skills;
    }

    public HeistStatus getStatus() {
        return status;
    }

    public void setStatus(HeistStatus status) {
        this.status = status;
    }

    public Set<MemberDTO> getMembers() {
        return members;
    }

    public void setMembers(Set<MemberDTO> members) {
        this.members = members;
    }

    public HeistOutcome getOutcome() {
        return outcome;
    }

    public void setOutcome(HeistOutcome outcome) {
        this.outcome = outcome;
    }
}
