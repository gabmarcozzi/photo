package it.uniroma3.siw.photo.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.photo.models.Order;

@Component
public class OrderValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Order.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		// non emepty user fields + valid email format
	}

}
