package org.agency04.software.moneyheist.repositories;

import org.agency04.software.moneyheist.entities.member.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Integer> {
}
