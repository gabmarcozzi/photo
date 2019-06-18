package it.uniroma3.siw.photo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.photo.exceptions.ServiceException;
import it.uniroma3.siw.photo.models.Photo;
import it.uniroma3.siw.photo.repositories.PhotoRepository;

@Service
@Transactional
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
		return (List<Photo>) this.photoRep.findAll();
	}

	/**
	 * Get all the photos with the specified name
	 * @param name
	 */
	public Photo findByName(String name) {
		return this.photoRep.findByName(name);
	}

	/**
	 * Returns whether a photo with the given name exists in the system.
	 * @param name
	 */
	public boolean existsByName(String name) throws ServiceException {
		if(name == null) throw new ServiceException("PhotoService.existsByName: null name");
		return photoRep.existsByName(name);
	}
}
