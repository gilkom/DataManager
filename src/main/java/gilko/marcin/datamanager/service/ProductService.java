package gilko.marcin.datamanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gilko.marcin.datamanager.model.Product;
import gilko.marcin.datamanager.repository.ProductRepository;

@Service
@Transactional
public class ProductService {
	@Autowired
	private ProductRepository repo;
	
	private List<Product> listAll(){
		return repo.findAll();
	}
	
	private void save(Product product) {
		repo.save(product);
	}
	
	private Product get(long id) {
		return repo.findById(id).get();
	}
	
	private void delete(long id) {
		repo.deleteById(id);
	}
}
