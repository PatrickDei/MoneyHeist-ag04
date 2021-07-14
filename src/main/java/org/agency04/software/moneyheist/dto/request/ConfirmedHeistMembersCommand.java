package org.agency04.software.moneyheist.dto.request;

import org.agency04.software.moneyheist.service.HeistService;
import org.agency04.software.moneyheist.validation.ValidMember;

import javax.validation.constraints.NotNull;
import java.util.List;

public class ConfirmedHeistMembersCommand {

    @NotNull
    @ValidMember(message = "Some of the members cannot participate", service = HeistService.class)
    private List<String> members;

    public ConfirmedHeistMembersCommand(){}

    public ConfirmedHeistMembersCommand(List<String> members) {
        this.members = members;
    }

    public List<String> getMembers() {
        return members;
    }
}
