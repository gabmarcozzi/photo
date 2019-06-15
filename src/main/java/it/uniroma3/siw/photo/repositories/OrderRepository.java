package it.uniroma3.siw.photo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.photo.models.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

}
