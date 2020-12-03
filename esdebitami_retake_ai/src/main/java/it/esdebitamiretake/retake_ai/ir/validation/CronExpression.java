package it.esdebitamiretake.retake_ai.ir.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.text.ParseException;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;


@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = { CronExpression.CronValidator.class })
public @interface CronExpression {

	String message() default "Invalid cron expression";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
	@Retention(RUNTIME)
	@Documented
	@interface List {

		CronExpression[] value();
	}

	public static class CronValidator implements ConstraintValidator<CronExpression, CharSequence> {

		private String message;

		@Override
		public void initialize(CronExpression constraintAnnotation) {
			this.message = constraintAnnotation.message();
		}
		
		@Override
		public boolean isValid(CharSequence value, ConstraintValidatorContext context) {

			boolean valid = true;

			if (value != null) {
				
				try {
				
					new org.quartz.CronExpression(value.toString());

				}catch (ParseException e) {

					context.disableDefaultConstraintViolation();
					context.buildConstraintViolationWithTemplate(this.message==null?e.getMessage():this.message).addConstraintViolation();
					valid = false;
				}
			}

			return valid;
		}
	}
}