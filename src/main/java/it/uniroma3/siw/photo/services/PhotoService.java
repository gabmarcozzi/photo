package it.uniroma3.siw.photo.services;

import java.util.List;

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

	/**
	 * Get a photo from DB
	 * @param id
	 */
	public Photo findById(Long id) {
		return this.photoRep.findById(id).orElse(null);
	}

	/**
	 * Get all the photos
	 */
	public List<Photo> findAll() {
		return  (List<Photo>) this.photoRep.findAll();
	}
	
}
