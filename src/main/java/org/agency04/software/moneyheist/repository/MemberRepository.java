package org.agency04.software.moneyheist.repository;

import org.agency04.software.moneyheist.entity.member.Member;
import org.agency04.software.moneyheist.repository.constant.SQLConstants;
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

    @Query(value = SQLConstants.findMembersForHeist, nativeQuery = true)
    List<Member> findMembersForHeist(@Param("heistId") Integer heistId);

    @Query(value = SQLConstants.findEligibleMembers, nativeQuery = true)
    List<Member> findEligibleMembers(@Param("name") String name, @Param("level") Integer level);

    boolean existsByEmail(String email);

    @Query(value = SQLConstants.isMemberParticipatingInAnotherHeist, nativeQuery = true)
    boolean isParticipatingInAnotherHeist(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = SQLConstants.removeSkillFromMember, nativeQuery = true)
    Integer removeSkillFromMember(@Param("id") Integer id, @Param("name") String name);
}
