package org.product.warranty.pro.beans.custom.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang.StringUtils;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String>{
	
	String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}";
	
	@Override
	public void initialize(ValidPassword constraintAnnotation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(StringUtils.isNotEmpty(value.trim())) {
			return value.matches(pattern);
		}
		return false;
	}

}
