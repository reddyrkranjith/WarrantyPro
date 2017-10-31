package org.product.warranty.pro.beans.custom.validations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.product.warranty.pro.beans.custom.validations.CheckAtLeastOneNotNullValidator;

@Documented
@Constraint(validatedBy = CheckAtLeastOneNotNullValidator.class)
@Target({ ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckAtLeastOneNotNull {
	
	String message() default "Please enter email/username";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String[] fieldNames();
}
