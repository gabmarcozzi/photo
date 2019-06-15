package it.uniroma3.siw.photo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.photo.exceptions.ServiceException;
import it.uniroma3.siw.photo.models.Photo;
import it.uniroma3.siw.photo.repositories.PhotoRepository;

@Service
public class PhotoService {

	@Autowired
	private PhotoRepository photoRep;
	
	
	/**
	 * Saves a new photo into the system.
	 * @param photo, must not be null
	 * @throws ServiceException
	 */
	public void save(Photo photo) throws ServiceException {
		
		if(photo == null)
			throw new ServiceException("Trying to save a null photo.");
		
		photoRep.save(photo);
	}
	
}
