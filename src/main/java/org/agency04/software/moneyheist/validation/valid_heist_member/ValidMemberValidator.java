package org.agency04.software.moneyheist.validation.valid_heist_member;

import org.agency04.software.moneyheist.interceptors.CustomInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ValidMemberValidator implements ConstraintValidator<ValidMember, List<String>> {

    @Autowired
    private CustomInterceptor interceptor;

    @Autowired
    private ApplicationContext context;

    private MemberIsValid service;

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
        Integer heistId = interceptor.getLastUrlId();
        for(String s : strings){
            if(!this.service.memberIsValid(s, heistId))
                return false;
        }
        return true;
    }
}
