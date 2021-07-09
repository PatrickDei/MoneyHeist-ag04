package org.agency04.software.moneyheist.repositories.heist;

import org.agency04.software.moneyheist.entities.heist.Heist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeistRepository extends CrudRepository<Heist, Integer> {

    List<Heist> findAll();

    boolean existsByName(String name);
}
