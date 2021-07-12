package org.agency04.software.moneyheist.repository;

import org.agency04.software.moneyheist.entity.member.Member;
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

    Optional<Member> findOneByEmail(String email);

    Optional<Member> findFirstByNameIgnoreCase(String name);

    @Query(value = "SELECT * FROM Member WHERE id IN (SELECT Member_id FROM Heist_Member WHERE Heist_id = :heistId)", nativeQuery = true)
    List<Member> findMembersForHeist(@Param("heistId") Integer heistId);

    @Query(value = "SELECT * FROM Member WHERE id IN " +
            "(SELECT Member_id FROM Member_skill WHERE Skill_id IN " +
            "(SELECT id FROM Skill WHERE name LIKE :name AND level >= :level))", nativeQuery = true)
    List<Member> findEligibleMembers(@Param("name") String name, @Param("level") Integer level);

    boolean existsByEmail(String email);

    @Query(value = "SELECT CASE WHEN (COUNT(*) > 0) THEN TRUE ELSE FALSE END " +
            "FROM Heist_Member WHERE Heist_Member.Member_id = :id", nativeQuery = true)
    boolean isParticipatingInAnotherHeist(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Member_Skill WHERE Member_Id = :id AND Skill_id IN (SELECT id FROM Skill WHERE name = :name)", nativeQuery = true)
    Integer removeSkillFromMember(@Param("id") Integer id, @Param("name") String name);
}
