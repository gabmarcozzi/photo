package it.uniroma3.siw.photo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.photo.exceptions.ServiceException;
import it.uniroma3.siw.photo.models.Album;
import it.uniroma3.siw.photo.repositories.AlbumRepository;

@Service
@Transactional
public class AlbumService {

	@Autowired
	private AlbumRepository alRep;
	
	
	/**
	 * Saves a new album into the system.
	 * @param album, must not be null
	 * @throws ServiceException
	 */
	public void save(Album album) throws ServiceException {
		if(album == null) throw new ServiceException("AlbumService.save: null album");
		alRep.save(album);
	}

	/**
	 * Get the album with the specified name
	 * @param name
	 */
	public Album findByName(String name) {
		return this.alRep.findByName(name);
	}

	/**
	 * Get all the albums
	 */
	public List<Album> findAll() {
		return (List<Album>) this.alRep.findAll();
	}

	/**
	 * Returns whether an album with the given name exists in the system.
	 * @param name
	 */
	public boolean existsByName(String name) throws ServiceException {
		if(name == null) throw new ServiceException("AlbumService.existsByName: null name");
		return alRep.existsByName(name);
	}
	
	/**
	 * Returns whether the album exists in the database. 
	 * @param al
	 * @return
	 * @throws ServiceException
	 */
	public boolean exists(Album al) throws ServiceException {
		return existsByName(al.getName());
	}
	
}
