package tam.example.SpringDataPro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tam.example.SpringDataPro.Entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{
	
}
