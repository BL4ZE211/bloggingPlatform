package com.nitheesh.bloggingplatform.annotation;

import com.nitheesh.bloggingplatform.aspect.UniqueEmailValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueEmailValidation.class)
public @interface   UniqueEmail {
    String message() default "Email is already being used";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
