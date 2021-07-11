package org.agency04.software.moneyheist.repositories;

import org.agency04.software.moneyheist.entities.member.Member;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends CrudRepository<Member, Integer> {
    List<Member> findAll();

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Heist_Member_Skill WHERE Heist_Member_Id = :id AND Skill_id IN (SELECT id FROM Skill WHERE name = :name)", nativeQuery = true)
    Integer removeSkillFromMember(@Param("id") Integer id, @Param("name") String name);

    boolean existsByEmail(String email);

    @Query(value = "SELECT * FROM Heist_Member WHERE id IN " +
            "(SELECT Heist_Member_id FROM Heist_Member_skill WHERE Skill_id IN " +
            "(SELECT id FROM Skill WHERE name LIKE :name AND level >= :level))" +
            "AND status IN ('AVAILABLE', 'RETIRED')", nativeQuery = true)
    List<Member> findEligibleMembers(@Param("name") String name, @Param("level") Integer level);

    Optional<Member> findFirstByNameIgnoreCase(String name);

    @Query(value = "SELECT CASE WHEN (COUNT(*) > 0) THEN TRUE ELSE FALSE END " +
            "FROM Heist_Heist_Member WHERE Heist_Heist_Member.Member_id = :id", nativeQuery = true)
    boolean isParticipatingInAnotherHeist(@Param("id") Integer id);

    Optional<Member> findOneByEmail(String email);
}
