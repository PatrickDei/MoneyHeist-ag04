package org.agency04.software.moneyheist.validation.validators.valid_main_skill;

import org.agency04.software.moneyheist.interceptors.CustomInterceptor;
import org.agency04.software.moneyheist.repositories.member.MemberRepository;
import org.agency04.software.moneyheist.validation.request_entities.MemberCommand;
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
                        interceptor.getLastUrlId()
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