package org.agency04.software.moneyheist.repositories.heist;

import org.agency04.software.moneyheist.entities.heist.Heist;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HeistRepository extends CrudRepository<Heist, Integer> {

    List<Heist> findAll();

    boolean existsByName(String name);
}
