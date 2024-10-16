package tien.java.web.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import tien.java.web.service.PhieuNhapKhoService;

public class maPhieuValidate implements ConstraintValidator<MaPhieuExist, String> {

	@Autowired
	private PhieuNhapKhoService phieuNhapKhoService;
	

	@Override
	public void initialize(MaPhieuExist constraintAnnotation) {
	}
	
	private boolean create;

	/**
	 * Account: Trần Minh Tiến Birthday: 1999/04/15
	 */
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null || value.length() == 0) {
			return true;
		}
		if (phieuNhapKhoService == null) {
			return true;
		}
		boolean ExistMP = phieuNhapKhoService.maPhieuExist(value);
		return create || ExistMP;
	}

}
