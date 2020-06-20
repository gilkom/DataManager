package gilko.marcin.datamanager.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import gilko.marcin.datamanager.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	@Query(value ="SELECT p FROM Product p WHERE CONCAT(p.name, p.brand, p.madein, p.price) LIKE %:keyword%")
	public Page<Product> search(@Param("keyword") String keyword, Pageable pageable);
}
