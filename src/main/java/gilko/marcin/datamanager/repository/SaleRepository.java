package gilko.marcin.datamanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import gilko.marcin.datamanager.model.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long>{

}
