package it.uniroma3.siw.photo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.photo.models.Photo;

@Repository
public interface PhotoRepository extends CrudRepository<Photo, Long> {

    public List<Photo> findByName(String name);
}
