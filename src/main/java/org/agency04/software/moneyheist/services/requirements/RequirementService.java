package org.agency04.software.moneyheist.services.requirements;

import org.agency04.software.moneyheist.entities.requirement.HeistRequirement;

import java.util.List;

public interface RequirementService {

    List<HeistRequirement> findAll();
}
