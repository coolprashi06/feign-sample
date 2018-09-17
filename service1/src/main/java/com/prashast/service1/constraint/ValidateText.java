package com.prashast.service1.constraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE, ElementType.TYPE_PARAMETER })
@Constraint(validatedBy = TextValidator.class)
public @interface ValidateText {
    String message() default
            "input text passed is not valid!";
    String value();

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
