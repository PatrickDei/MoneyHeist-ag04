package org.agency04.software.moneyheist.repository;

import org.agency04.software.moneyheist.entity.member.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
}
