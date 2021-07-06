package org.agency04.software.moneyheist.services.heist;

import org.agency04.software.moneyheist.dto.heist.HeistDTO;

import java.util.List;

public interface HeistService {

    List<HeistDTO> findAll();
}
