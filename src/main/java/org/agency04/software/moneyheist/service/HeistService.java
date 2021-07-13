package org.agency04.software.moneyheist.service;

import org.agency04.software.moneyheist.dto.response.HeistDTO;
import org.agency04.software.moneyheist.dto.response.HeistMembersDTO;
import org.agency04.software.moneyheist.dto.response.HeistRequirementDTO;
import org.agency04.software.moneyheist.entity.heist.Heist;
import org.agency04.software.moneyheist.entity.heist.HeistStatus;
import org.agency04.software.moneyheist.dto.request.HeistCommand;
import org.agency04.software.moneyheist.validation.interfaces.FieldValueExists;
import org.agency04.software.moneyheist.validation.interfaces.MemberIsValid;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface HeistService extends FieldValueExists, MemberIsValid {

    List<HeistDTO> findAll();

    Optional<HeistDTO> findHeist(Integer id);

    List<Heist> findHeistsByOutcomeIsNull();

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
