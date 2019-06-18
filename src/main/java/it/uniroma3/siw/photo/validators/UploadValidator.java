package it.uniroma3.siw.photo.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.photo.exceptions.ServiceException;
import it.uniroma3.siw.photo.models.Album;
import it.uniroma3.siw.photo.models.Photo;
import it.uniroma3.siw.photo.models.UploadPhotoForm;
import it.uniroma3.siw.photo.services.AlbumService;
import it.uniroma3.siw.photo.services.PhotoService;

@Component
public class UploadValidator implements Validator {
	
	@Autowired
	private AlbumService as;
	
	@Autowired
	private PhotoService ps;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return UploadPhotoForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "photographerName", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "albumName", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "photoName", "required");
		
		UploadPhotoForm upf = (UploadPhotoForm) target;
		
		try {
			if(ps.existsByName(upf.getPhotoName())) {
				errors.rejectValue("photoName", "duplicated");
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
