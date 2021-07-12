package org.agency04.software.moneyheist.repository;

import org.agency04.software.moneyheist.entity.requirement.HeistRequirement;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequirementRepository extends CrudRepository<HeistRequirement, Integer> {

    List<HeistRequirement> findAll();

    @Query(value = "SELECT * FROM Requirement WHERE id IN (SELECT Requirement_id FROM Heist_Requirement WHERE Heist_Id = :heistId)", nativeQuery = true)
    List<HeistRequirement> findRequirementsForHeist(@Param("heistId") Integer heistId);
}
