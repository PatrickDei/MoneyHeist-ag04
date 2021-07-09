package org.agency04.software.moneyheist.dto;

import java.util.Date;
import java.util.List;

public class HeistDTO {

    private Integer id;

    private String name;

    private String location;

    private Date startTime;

    private Date endTime;

    private List<HeistRequirementDTO> skills;

    public HeistDTO(Integer id, String name, String location, Date startTime, Date endTime, List<HeistRequirementDTO> skills) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.startTime = startTime;
        this.endTime = endTime;
        this.skills = skills;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
