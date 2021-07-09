package org.agency04.software.moneyheist.validation.validMainSkill;

import org.agency04.software.moneyheist.interceptors.CustomInterceptor;
import org.agency04.software.moneyheist.repositories.member.MemberRepository;
import org.agency04.software.moneyheist.validation.requestEntities.MemberCommand;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidMainSkillValidator implements ConstraintValidator<ValidMainSkill, MemberCommand> {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private CustomInterceptor interceptor;

    @Override
    public void initialize(ValidMainSkill constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MemberCommand memberCommand, ConstraintValidatorContext constraintValidatorContext) {
        return memberCommand.getMainSkill() == null
                ||
                memberRepository
                .findById(
                        interceptor.getMemberId()
                ).map(value -> value
                    .getSkills().stream()
                    .anyMatch(
                            s -> s.getName().equalsIgnoreCase(memberCommand.getMainSkill())
                    )
                ).orElse(false)
                ||
                (
                    memberCommand.getSkills() != null
                    &&
                    memberCommand
                        .getSkills().stream()
                        .anyMatch( s -> s.getName().equalsIgnoreCase(memberCommand.getMainSkill())));
    }
}