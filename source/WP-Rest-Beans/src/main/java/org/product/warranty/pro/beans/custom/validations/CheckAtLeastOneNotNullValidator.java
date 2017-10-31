package org.product.warranty.pro.beans.custom.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.PropertyUtils;

public class CheckAtLeastOneNotNullValidator implements ConstraintValidator<CheckAtLeastOneNotNull, Object> {

	private String[] fieldNames;

	public void initialize(CheckAtLeastOneNotNull constraintAnnotation) {
		this.fieldNames = constraintAnnotation.fieldNames();
	}

	public boolean isValid(Object object, ConstraintValidatorContext constraintContext) {

		if (object == null)
			return true;
		try {
			for (String fieldName : fieldNames) {
				Object property = PropertyUtils.getProperty(object, fieldName);
				if (property != null)
					return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

}
