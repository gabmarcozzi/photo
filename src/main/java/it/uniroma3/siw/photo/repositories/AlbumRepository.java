package it.uniroma3.siw.photo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.photo.models.Album;

@Repository
public interface AlbumRepository extends CrudRepository<Album, Long> {

    public Album findByName(String name);
    
    public boolean existsByName(String name);
    
}
