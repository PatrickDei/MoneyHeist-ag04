package org.agency04.software.moneyheist.validation.validator;

import org.agency04.software.moneyheist.dto.request.MemberCommand;
import org.agency04.software.moneyheist.interceptor.FindIdentityByUrlInterceptor;
import org.agency04.software.moneyheist.service.MemberService;
import org.agency04.software.moneyheist.validation.ValidMainSkill;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidMainSkillValidator implements ConstraintValidator<ValidMainSkill, MemberCommand> {

    private final MemberService memberService;
    private final FindIdentityByUrlInterceptor identityByUrlInterceptor;

    public ValidMainSkillValidator(MemberService memberService, FindIdentityByUrlInterceptor identityByUrlInterceptor) {
        this.memberService = memberService;
        this.identityByUrlInterceptor = identityByUrlInterceptor;
    }

    @Override
    public void initialize(ValidMainSkill constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MemberCommand memberCommand, ConstraintValidatorContext constraintValidatorContext) {
        return memberCommand.getMainSkill() == null
                ||
                memberService
                .findMemberById(
                        identityByUrlInterceptor.getLastUrlId()
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