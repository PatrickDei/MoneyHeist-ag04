package org.agency04.software.moneyheist.members.repository;

import org.agency04.software.moneyheist.members.Member;
import org.agency04.software.moneyheist.skills.Skill;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
public class MemberSkillRelationshipInserter {

    @PersistenceContext
    private final EntityManager entityManager;

    public MemberSkillRelationshipInserter(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void AddSkillsForMember(Member member, List<Skill> skills){
        for (Skill s: skills) {
            Query query = entityManager.createNativeQuery(
                    "INSERT INTO Heist_Member_Skill (Heist_Member_Id, name, level) VALUES (:id, :name, :level);");
            query.setParameter("id", member.getId());
            query.setParameter("name", s.getName());
            query.setParameter("level", s.getSkillLevel());

            query.executeUpdate();
        }
    }
}
