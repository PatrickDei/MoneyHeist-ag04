package org.agency04.software.moneyheist.entities.heist;


import org.agency04.software.moneyheist.entities.requirement.HeistRequirement;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "heist")
public class Heist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "location")
    private String location;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "end_time")
    private Date endTime;

    @ManyToMany( cascade = {CascadeType.ALL})
    @JoinTable(
            name = "Heist_Heist_requirement",
            joinColumns = @JoinColumn(name = "heist_id"),
            inverseJoinColumns = @JoinColumn(name = "heist_requirement_id")
    )
    private Set<HeistRequirement> requirements;

    public Heist(){}

    public Heist(String name, String location, Date startTime, Date endTime, Set<HeistRequirement> requirements) {
        this.name = name;
        this.location = location;
        this.startTime = startTime;
        this.endTime = endTime;
        this.requirements = requirements;
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

    public Set<HeistRequirement> getRequirements() {
        return requirements;
    }

    public void setRequirements(Set<HeistRequirement> requirements) {
        this.requirements = requirements;
    }

    public void updateRequirements(Set<HeistRequirement> newRequirements){
        for(HeistRequirement r : newRequirements){
            if(requirements.stream()
                    .map(req -> req.getSkill().getName()).collect(Collectors.toList())
                    .contains(r.getSkill().getName()))
            {
                requirements.remove(
                        requirements.stream()
                                .filter(skill -> skill.getSkill().getName().equals(r.getSkill().getName()))
                                .findFirst()
                                .orElse(null));
            }

            requirements.add(r);
        }
    }
}