package it.uniroma3.siw.photo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.photo.exceptions.ServiceException;
import it.uniroma3.siw.photo.models.Order;
import it.uniroma3.siw.photo.repositories.OrderRepository;

@Service
@Transactional
public class OrderService {

	@Autowired
	private OrderRepository orderRep;
	
	
	/**
	 * Saves a new order into the system.
	 * @param order, must not be null
	 * @throws ServiceException
	 */
	public void save(Order order) throws ServiceException {
		
		if(order == null)
			throw new ServiceException("Trying to save a null order.");
		
		orderRep.save(order);
	}

	/**
	 * Get all the orders
	 */
	public List<Order> findAll() {
		return (List<Order>) this.orderRep.findAll();
	}
	
	/**
	 * Get the order with the specified id
	 * @param id
	 */
	public Order findById(Long id) {
		return this.orderRep.findById(id).orElse(null);
	}
}
