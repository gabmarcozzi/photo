package it.uniroma3.siw.photo.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.photo.models.Album;
import it.uniroma3.siw.photo.models.Photo;
import it.uniroma3.siw.photo.models.Photographer;
import it.uniroma3.siw.photo.models.UploadPhotoForm;
import it.uniroma3.siw.photo.services.AlbumService;
import it.uniroma3.siw.photo.services.PhotoService;
import it.uniroma3.siw.photo.services.PhotographerService;

@Component
public class UploadValidator implements Validator {

	@Autowired
	private PhotoService photoService;

	@Autowired
	private PhotographerService photographerService;

	@Autowired
	private AlbumService albumService;


	@Override
	public boolean supports(Class<?> clazz) {
		return UploadPhotoForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "photographerName", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "albumName", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "photoName", "required");
		
		// Syntax errors, return. Otherwise, look for logic errors.
		if(errors.hasErrors())
			return;

		UploadPhotoForm upf = (UploadPhotoForm) target;

		Photographer photographer = photographerService.findByName(upf.getPhotographerName());
		Album album = albumService.findByName(upf.getAlbumName());
		Photo photo = photoService.findByName(upf.getPhotoName());
		
		if(album != null && ((photographer != null && !album.getPh().equals(photographer) || photographer == null)))
			errors.rejectValue("albumName", "wrongalbum");
		
		if(photo != null)
			errors.rejectValue("photoName", "duplicated");
	}

}
