package gilko.marcin.datamanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gilko.marcin.datamanager.model.Sale;
import gilko.marcin.datamanager.repository.SaleRepository;

@Service
@Transactional
public class SaleService {
	@Autowired
	private SaleRepository repo;
	
	public List<Sale> list(){
		return repo.findAll();
	}
	public void save(Sale sale) {
		repo.save(sale);
	}
	public Sale get(long id) {
		return repo.findById(id).get();
	}
	public void update(Sale sale) {
		
	}
	public void delete(long id) {
		repo.deleteById(id);
		
	}
}
