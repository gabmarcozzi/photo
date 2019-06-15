package it.uniroma3.siw.photo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.photo.exceptions.ServiceException;
import it.uniroma3.siw.photo.models.Photographer;
import it.uniroma3.siw.photo.repositories.PhotographerRepository;

@Service
public class PhotographerService {

	@Autowired
	private PhotographerRepository phRep;
	
	
	/**
	 * Saves a new photographer into the system.
	 * @param ph, must not be null
	 * @throws ServiceException
	 */
	public void save(Photographer ph) throws ServiceException {
		
		if(ph == null)
			throw new ServiceException("Trying to save a null photographer.");
		
		phRep.save(ph);
	}
	
}
