package org.agency04.software.moneyheist.services.heist;

import org.agency04.software.moneyheist.dto.HeistDTO;
import org.agency04.software.moneyheist.dto.HeistMembersDTO;
import org.agency04.software.moneyheist.dto.HeistRequirementDTO;
import org.agency04.software.moneyheist.entities.heist.Heist;
import org.agency04.software.moneyheist.entities.heist.HeistStatus;
import org.agency04.software.moneyheist.validation.request_entities.HeistCommand;
import org.agency04.software.moneyheist.validation.validators.unique_field.FieldValueExists;
import org.agency04.software.moneyheist.validation.validators.valid_heist_member.MemberIsValid;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface HeistService extends FieldValueExists, MemberIsValid {

    List<HeistDTO> findAll();

    Optional<HeistDTO> findHeist(Integer id);

    Set<HeistMembersDTO> getHeistMembers(Integer heistId);

    Set<HeistRequirementDTO> getHeistRequirements(Integer id);

    Integer saveHeist(HeistCommand heist) throws ParseException;

    Integer updateHeistSkills(HeistCommand heist, Integer heistId);

    HeistDTO getEligibleHeistMembers(Integer heistId);

    Integer confirmHeistMembers(Integer heistId, List<String> memberNames);

    HeistStatus getHeistStatus(Integer heistId);

    Optional<Heist> findHeistById(Integer id);

    boolean heistCanBeStarted(Integer heistId);

    void startHeist(Integer id);

    void finishHeist(Integer id);
}
