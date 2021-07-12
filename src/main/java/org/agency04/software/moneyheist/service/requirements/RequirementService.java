package org.agency04.software.moneyheist.service.requirements;

import org.agency04.software.moneyheist.entity.requirement.HeistRequirement;

import java.util.List;

public interface RequirementService {

    List<HeistRequirement> findAll();
}
