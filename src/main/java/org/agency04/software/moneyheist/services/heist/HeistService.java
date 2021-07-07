package org.agency04.software.moneyheist.services.heist;

import org.agency04.software.moneyheist.dto.heist.HeistDTO;
import org.agency04.software.moneyheist.validation.uniquefield.FieldValueExists;

import java.util.List;

public interface HeistService extends FieldValueExists {

    List<HeistDTO> findAll();
}
