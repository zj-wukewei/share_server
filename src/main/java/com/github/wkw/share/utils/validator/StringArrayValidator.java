package com.github.wkw.share.utils.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class StringArrayValidator implements ConstraintValidator<StringArray, String> {

    private String[] mValue;

    @Override
    public void initialize(StringArray constraintAnnotation) {
        this.mValue = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String str, ConstraintValidatorContext constraintValidatorContext) {
        return Arrays.asList(mValue).contains(str);
    }
}
