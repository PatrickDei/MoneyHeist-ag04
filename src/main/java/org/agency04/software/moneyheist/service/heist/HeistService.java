package org.agency04.software.moneyheist.service.heist;

import org.agency04.software.moneyheist.dto.response_entity.HeistDTO;
import org.agency04.software.moneyheist.dto.response_entity.HeistMembersDTO;
import org.agency04.software.moneyheist.dto.response_entity.HeistRequirementDTO;
import org.agency04.software.moneyheist.entity.heist.Heist;
import org.agency04.software.moneyheist.entity.heist.HeistStatus;
import org.agency04.software.moneyheist.dto.request_entity.HeistCommand;
import org.agency04.software.moneyheist.validation.unique_field.FieldValueExists;
import org.agency04.software.moneyheist.validation.valid_heist_member.MemberIsValid;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface HeistService extends FieldValueExists, MemberIsValid {

    List<HeistDTO> findAll();

    Optional<HeistDTO> findHeist(Integer id);

    Set<HeistMembersDTO> getHeistMembers(Integer heistId);

    Set<HeistRequirementDTO> getHeistRequirements(Integer id);

    HeistDTO getEligibleHeistMembers(Integer heistId);

    HeistStatus getHeistStatus(Integer heistId);

    Integer saveHeist(HeistCommand heist) throws ParseException;

    Integer updateHeistSkills(HeistCommand heist, Integer heistId);

    Integer confirmHeistMembers(Integer heistId, List<String> memberNames);

    boolean heistCanBeStarted(Integer heistId);

    void startHeist(Integer id);

    void finishHeist(Integer id);

    void upgradeMemberSkills(Heist heist);
}
