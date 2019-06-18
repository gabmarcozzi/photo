package it.uniroma3.siw.photo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.photo.exceptions.ServiceException;
import it.uniroma3.siw.photo.models.Photographer;
import it.uniroma3.siw.photo.repositories.PhotographerRepository;

@Service
@Transactional
public class PhotographerService {

	@Autowired
	private PhotographerRepository phRep;
	
	
	/**
	 * Saves a new photographer into the system.
	 * Pre: ph mustn't be recorder in the system
	 * Post: ph is persisted into the system
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

	/**
	 * Returns whether a photographer with the given name exists in the system.
	 * @param name
	 */
	public boolean existsByName(String name) throws ServiceException {
		if(name == null) throw new ServiceException("PhotographerService.existsByName: null name");
		return phRep.existsByName(name);
	}
	
	/**
	 * Returns whether the photographer exists in the database. 
	 * @param ph
	 * @return
	 * @throws ServiceException
	 */
	public boolean exists(Photographer ph) throws ServiceException {
		return existsByName(ph.getName());
	}
}
