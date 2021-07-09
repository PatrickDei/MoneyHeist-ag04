package org.agency04.software.moneyheist.repositories.member;

import org.agency04.software.moneyheist.entities.member.Member;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface MemberRepository extends CrudRepository<Member, Integer> {
    List<Member> findAll();

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Heist_Member_Skill WHERE Heist_Member_Id = :id AND Skill_id IN (SELECT id FROM Skill WHERE name = :name)", nativeQuery = true)
    Integer removeSkillFromMember(@Param("id") Integer id, @Param("name") String name);

    boolean existsByEmail(String email);
}
