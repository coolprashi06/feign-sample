package com.prashast.service1.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TextValidator implements ConstraintValidator<ValidateText, String> {

    private String validString;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(!s.startsWith(this.validString)){
            return false;
        }
        return true;
    }

    @Override
    public void initialize(ValidateText constraintAnnotation) {
        this.validString = constraintAnnotation.value();
    }
}
