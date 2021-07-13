package org.agency04.software.moneyheist.validation.validator;

import org.agency04.software.moneyheist.interceptor.FindIdentityByUrlInterceptor;
import org.agency04.software.moneyheist.validation.interfaces.MemberIsValid;
import org.agency04.software.moneyheist.validation.ValidMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ValidMemberValidator implements ConstraintValidator<ValidMember, List<String>> {

    @Autowired
    private ApplicationContext context;
    private final FindIdentityByUrlInterceptor identityByUrlInterceptor;

    private MemberIsValid service;

    public ValidMemberValidator(FindIdentityByUrlInterceptor identityByUrlInterceptor) {
        this.identityByUrlInterceptor = identityByUrlInterceptor;
    }

    @Override
    public void initialize(ValidMember constraintAnnotation) {
        Class<? extends MemberIsValid> serviceClass = constraintAnnotation.service();
        String serviceQualifier = constraintAnnotation.serviceQualifier();

        if (!serviceQualifier.equals("")) {
            this.service = this.context.getBean(serviceQualifier, serviceClass);
        } else {
            this.service = this.context.getBean(serviceClass);
        }
    }

    @Override
    public boolean isValid(List<String> strings, ConstraintValidatorContext constraintValidatorContext) {
        Integer heistId = identityByUrlInterceptor.getLastUrlId();
        for(String s : strings){
            if(!this.service.memberIsValid(s, heistId))
                return false;
        }
        return true;
    }
}
