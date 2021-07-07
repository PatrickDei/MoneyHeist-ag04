package org.agency04.software.moneyheist.services.heist;

import org.agency04.software.moneyheist.dto.heist.HeistDTO;
import org.agency04.software.moneyheist.validation.requestEntity.heist.HeistCommand;
import org.agency04.software.moneyheist.validation.uniqueField.FieldValueExists;

import java.text.ParseException;
import java.util.List;

public interface HeistService extends FieldValueExists {

    List<HeistDTO> findAll();

    Integer saveHeist(HeistCommand heist) throws ParseException;
}
