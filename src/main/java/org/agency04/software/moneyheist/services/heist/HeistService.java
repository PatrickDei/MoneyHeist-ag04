package org.agency04.software.moneyheist.services.heist;

import org.agency04.software.moneyheist.dto.EligibleHeistMembersDTO;
import org.agency04.software.moneyheist.dto.HeistDTO;
import org.agency04.software.moneyheist.entities.heist.Heist;
import org.agency04.software.moneyheist.entities.heist.HeistStatus;
import org.agency04.software.moneyheist.validation.requestEntities.HeistCommand;
import org.agency04.software.moneyheist.validation.uniqueField.FieldValueExists;
import org.agency04.software.moneyheist.validation.validHeistMember.MemberIsValid;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public interface HeistService extends FieldValueExists, MemberIsValid {

    List<HeistDTO> findAll();

    Integer saveHeist(HeistCommand heist) throws ParseException;

    Integer updateHeistSkills(HeistCommand heist, Integer heistId);

    EligibleHeistMembersDTO getEligibleHeistMembers(Integer heistId);

    Integer confirmHeistMembers(Integer heistId, List<String> memberNames);

    HeistStatus getHeistStatus(Integer heistId);

    Optional<Heist> findHeistById(Integer id);

    boolean heistCanBeStarted(Integer heistId);

    void startHeist(Integer id);
}
