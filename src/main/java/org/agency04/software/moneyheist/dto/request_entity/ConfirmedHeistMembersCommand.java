package org.agency04.software.moneyheist.dto.request_entity;

import org.agency04.software.moneyheist.service.heist.HeistService;
import org.agency04.software.moneyheist.validation.valid_heist_member.ValidMember;

import javax.validation.constraints.NotNull;
import java.util.List;

public class ConfirmedHeistMembersCommand {

    @NotNull
    @ValidMember(message = "Some of the members cannot participate", service = HeistService.class)
    private List<String> members;

    public ConfirmedHeistMembersCommand(List<String> members) {
        this.members = members;
    }

    public ConfirmedHeistMembersCommand(){}

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }
}
