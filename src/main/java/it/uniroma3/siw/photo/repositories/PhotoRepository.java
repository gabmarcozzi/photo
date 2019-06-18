package it.uniroma3.siw.photo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.photo.models.Photo;

@Repository
public interface PhotoRepository extends CrudRepository<Photo, Long> {

    public Photo findByName(String name);
    
    public boolean existsByName(String name);
    
}
