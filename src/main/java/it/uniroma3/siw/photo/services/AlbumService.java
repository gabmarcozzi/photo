package it.uniroma3.siw.photo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.photo.exceptions.ServiceException;
import it.uniroma3.siw.photo.models.Album;
import it.uniroma3.siw.photo.repositories.AlbumRepository;

@Service
public class AlbumService {

	@Autowired
	private AlbumRepository alRep;
	
	
	/**
	 * Saves a new album into the system.
	 * @param album, must not be null
	 * @throws ServiceException
	 */
	public void save(Album album) throws ServiceException {
		
		if(album == null)
			throw new ServiceException("Trying to save a null album.");
		
		alRep.save(album);
	}
	
}
