package org.agency04.software.moneyheist.validation.requestEntities;

import org.agency04.software.moneyheist.services.heist.HeistService;
import org.agency04.software.moneyheist.validation.validHeistMember.ValidMember;

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
