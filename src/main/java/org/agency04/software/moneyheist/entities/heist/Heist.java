package org.agency04.software.moneyheist.entities.heist;


import org.agency04.software.moneyheist.entities.member.Member;
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

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(
            name = "Heist_Heist_requirement",
            joinColumns = @JoinColumn(name = "heist_id"),
            inverseJoinColumns = @JoinColumn(name = "heist_requirement_id")
    )
    private Set<HeistRequirement> requirements;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private HeistStatus status;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(
            name = "Heist_Heist_member",
            joinColumns = @JoinColumn(name = "Heist_id"),
            inverseJoinColumns = @JoinColumn(name = "Member_id")
    )
    private Set<Member> members;

    @Column(name = "outcome")
    private HeistOutcome outcome;

    public Heist(){}

    public Heist(String name, String location, Date startTime, Date endTime, Set<HeistRequirement> requirements, HeistStatus status, HeistOutcome outcome) {
        this.name = name;
        this.location = location;
        this.startTime = startTime;
        this.endTime = endTime;
        this.requirements = requirements;
        this.status = status;
        this.outcome = outcome;
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

    public HeistStatus getStatus() {
        return status;
    }

    public void setStatus(HeistStatus status) {
        this.status = status;
    }

    public Set<Member> getMembers() {
        return members;
    }

    public void setMembers(Set<Member> members) {
        this.members = members;
    }

    public HeistOutcome getOutcome() {
        return outcome;
    }

    public void setOutcome(HeistOutcome outcome) {
        this.outcome = outcome;
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