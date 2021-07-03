package org.agency04.software.moneyheist.heist.members;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends CrudRepository<Member, Integer> {

    List<Member> findAll();

    //Integer insertMember(Member member);
}
