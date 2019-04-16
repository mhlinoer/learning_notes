package com.linoer.springtest.interfaces;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.time.LocalDate;

public @interface PastLocalDate {
    String message() default "{javax.validation.constraints.Past.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    class PastValidator implements ConstraintValidator<PastLocalDate, LocalDate>{
        public void initialize(PastLocalDate pastLocalDate){}
        @Override
        public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
            return localDate == null || localDate.isBefore(LocalDate.now());
        }
    }
}
