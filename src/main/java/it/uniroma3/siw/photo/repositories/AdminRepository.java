package it.uniroma3.siw.photo.repositories;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.photo.models.Admin;

public interface AdminRepository extends CrudRepository<Admin, Long> {

}