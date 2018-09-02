package tam.example.SpringDataPro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tam.example.SpringDataPro.Entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	public User findOneByUserName(String userName);
}
