package gilko.marcin.datamanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gilko.marcin.datamanager.model.Customer;
import gilko.marcin.datamanager.repository.CustomerRepository;

@Service
@Transactional
public class CustomerService {
	@Autowired
	private CustomerRepository repo;
	
	public List<Customer> listAll(){
		return repo.findAll();
	}
	public void save(Customer customer) {
		repo.save(customer);
	}
	public Customer get(long id) {
		return repo.findById(id).get();
	}
	public void delete(Long id) {
		repo.deleteById(id);
	}
}
