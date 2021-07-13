package org.agency04.software.moneyheist.repository.constant;

public final class SQLConstants {

    public static final String insertMemberIntoHeist = "INSERT INTO Heist_Member (heist_id, member_id) VALUES (:heistId, :memberId)";

    public static final String findRequirementsForHeist = "SELECT * FROM Requirement WHERE id IN (SELECT Requirement_id FROM Heist_Requirement WHERE Heist_Id = :heistId)";

    public static final String findMembersForHeist = "SELECT * FROM Member WHERE id IN (SELECT Member_id FROM Heist_Member WHERE Heist_id = :heistId)";

    public static final String findEligibleMembers = "SELECT * FROM Member WHERE id IN " +
            "(SELECT Member_id FROM Member_skill WHERE Skill_id IN " +
            "(SELECT id FROM Skill WHERE name LIKE :name AND level >= :level))";

    public static final String isMemberParticipatingInAnotherHeist = "SELECT CASE WHEN (COUNT(*) > 0) THEN TRUE ELSE FALSE END " +
            "FROM Heist_Member WHERE Heist_Member.Member_id = :id";

    public static final String removeSkillFromMember = "DELETE FROM Member_Skill WHERE Member_Id = :id AND Skill_id IN (SELECT id FROM Skill WHERE name = :name)";
}