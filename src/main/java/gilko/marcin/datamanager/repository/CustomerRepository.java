package gilko.marcin.datamanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import gilko.marcin.datamanager.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
