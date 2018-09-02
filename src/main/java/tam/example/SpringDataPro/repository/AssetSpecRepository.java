package tam.example.SpringDataPro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tam.example.SpringDataPro.Entity.Asset;

public interface AssetSpecRepository extends JpaRepository<Asset, Long>{

}
