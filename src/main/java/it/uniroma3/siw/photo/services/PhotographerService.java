package it.uniroma3.siw.photo.services;

import java.util.List;

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

	/**
	 * Get all the photographers
	 */
	public List<Photographer> findAll() {
		return (List<Photographer>) this.phRep.findAll();
	}

	/**
	 * Get the photographer with the specified name
	 * @param name
	 */
	public Photographer findByName(String name) {
		return this.phRep.findByName(name);
	}


	
}
