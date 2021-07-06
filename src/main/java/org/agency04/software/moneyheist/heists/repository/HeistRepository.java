package org.agency04.software.moneyheist.heists.repository;

import org.agency04.software.moneyheist.heists.Heist;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HeistRepository extends CrudRepository<Heist, Integer> {

    List<Heist> findAll();
}
