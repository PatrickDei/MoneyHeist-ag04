package org.agency04.software.moneyheist.repositories;

import org.agency04.software.moneyheist.entities.requirement.HeistRequirement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequirementRepository extends CrudRepository<HeistRequirement, Integer> {

    List<HeistRequirement> findAll();
}
