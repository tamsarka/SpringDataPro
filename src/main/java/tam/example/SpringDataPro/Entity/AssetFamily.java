package tam.example.SpringDataPro.Entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.json.JSONException;
import org.json.JSONObject;

@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AssetFamily {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long familyId;
	
	String assetFamilyName;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="family", cascade = CascadeType.ALL, orphanRemoval=true)
	Set<Asset> 	assetList  = new HashSet<>();
	
	public AssetFamily() {
		super();
	}

	public AssetFamily( String assetFamilyName) {
		super();
		this.assetFamilyName = assetFamilyName;
	}

	public String getAssetFamilyName() {
		return assetFamilyName;
	}

	public Long getFamilyId() {
		return familyId;
	}

	public void setFamilyId(Long familyId) {
		this.familyId = familyId;
	}

	public void setAssetFamilyName(String assetFamilyName) {
		this.assetFamilyName = assetFamilyName;
	}

	public Set<Asset> getAssetList() {
		return assetList;
	}

	public void setAssetList(Set<Asset> assetList) {
		this.assetList = assetList;
	}


	@Override
	public String toString() {
		System.out.println(" ...... IN TOSTRING ....");
        JSONObject jsonInfo = new JSONObject();
        try {
			jsonInfo.put("assetName", this.getAssetFamilyName());
			jsonInfo.put("assetId", this.getFamilyId());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return jsonInfo.toString();
	}
}
