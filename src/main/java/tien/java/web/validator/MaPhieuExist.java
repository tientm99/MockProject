package tien.java.web.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = maPhieuValidate.class)
@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface MaPhieuExist {
	public String message() default "Mã phiếu đã tồn tại";

	public Class<?>[] groups() default {};

	public Class<? extends Payload>[] payload() default {};
	
	 boolean create() default false;
}
