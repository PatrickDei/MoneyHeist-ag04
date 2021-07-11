package org.agency04.software.moneyheist.repositories;

import org.agency04.software.moneyheist.entities.heist.Heist;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface HeistRepository extends CrudRepository<Heist, Integer> {

    List<Heist> findAll();

    List<Heist> findByOutcomeIsNull();

    boolean existsByName(String name);

    @Transactional
    @Query(value = "INSERT INTO Heist_Heist_Member (heist_id, member_id) VALUES (:heistId, :memberId)", nativeQuery = true)
    void insertMemberIntoHeists(@Param("memberId") Integer memberId, @Param("heistId") Integer heistId);
}
