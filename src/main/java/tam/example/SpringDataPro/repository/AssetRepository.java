package tam.example.SpringDataPro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import tam.example.SpringDataPro.Entity.Asset;
import tam.example.SpringDataPro.Entity.AssetFamily;

public interface AssetRepository extends JpaRepository<Asset, Long> , JpaSpecificationExecutor<Asset>{

	public Asset findTopByFamilyOrderByAssetSerialNumberDesc(AssetFamily family);
	public Asset findFirstByFamilyOrderByAssetSerialNumberDesc(AssetFamily family);
	public List<Asset> findByFamilyOrderByAssetSerialNumberDesc(AssetFamily family);
}
