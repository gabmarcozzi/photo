package it.uniroma3.siw.photo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.photo.exceptions.ServiceException;
import it.uniroma3.siw.photo.models.Order;
import it.uniroma3.siw.photo.repositories.OrderRepository;

@Service
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
	
}
