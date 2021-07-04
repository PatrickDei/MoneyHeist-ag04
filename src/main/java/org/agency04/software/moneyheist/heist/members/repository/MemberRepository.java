package org.agency04.software.moneyheist.heist.members.repository;

import org.agency04.software.moneyheist.heist.members.Member;
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
    @Query(value = "DELETE FROM Heist_Member_Skill WHERE Heist_Member_Id = :id AND name = :name", nativeQuery = true)
    Integer removeSkillFromMember(@Param("id") Integer id, @Param("name") String name);
}
