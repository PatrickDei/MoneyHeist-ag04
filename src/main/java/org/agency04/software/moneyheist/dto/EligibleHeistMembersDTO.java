package org.agency04.software.moneyheist.dto;

import java.util.List;

public class EligibleHeistMembersDTO {

    private List<HeistRequirementDTO> skills;

    private List<EligibleMemberDTO> members;

    public EligibleHeistMembersDTO(List<HeistRequirementDTO> skills, List<EligibleMemberDTO> members) {
        this.skills = skills;
        this.members = members;
    }

    public List<HeistRequirementDTO> getSkills() {
        return skills;
    }

    public void setSkills(List<HeistRequirementDTO> skills) {
        this.skills = skills;
    }

    public List<EligibleMemberDTO> getMembers() {
        return members;
    }

    public void setMembers(List<EligibleMemberDTO> members) {
        this.members = members;
    }
}
