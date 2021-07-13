package org.agency04.software.moneyheist.group;

public class Group {

    public static class ValidationGroup {
        public interface OnlySkillsRequired { }

        public interface WholeObjectRequired { }
    }


    public static class JsonViewGroup {
        public interface MemberSkills{ }

        public interface EligibleMembers{ }

        public interface BasicHeistInfo{ }

        public interface HeistStatus{ }

        public interface HeistOutcome{ }
    }

}