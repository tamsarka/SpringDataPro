package tam.example.SpringDataPro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import tam.example.SpringDataPro.Entity.Asset;
import tam.example.SpringDataPro.Entity.AssetFamily;

public interface AssetFamilyRepo extends JpaRepository<AssetFamily, Long> , JpaSpecificationExecutor<Asset>{

}
