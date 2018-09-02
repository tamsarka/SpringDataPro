package tam.example.SpringDataPro.Entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.json.JSONException;
import org.json.JSONObject;

@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Asset {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long assetId;
	
	String assetName;
	String assetSerialNumber;
	String description;
	
	@ManyToOne
	@JoinColumn(name = "familiId")
	AssetFamily family;
	
	
	/**
	 * Lazy loading with public access type will not work because we are transferring the entity from controller 
	 * and as we are not invoking the user object it will not be loaded at all. So spring will not be able to prepare the json from the object. 
	 */
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	

	public Asset(String assetName, String assetSerialNumber, String description, User user, AssetFamily family) {
		super();
		this.assetName = assetName;
		this.assetSerialNumber = assetSerialNumber;
		this.description = description;
		this.user = user;
		this.family = family;
	}

	public Asset() {
		// TODO Auto-generated constructor stub
	}

	public Long getAssetId() {
		return assetId;
	}

	public void setAssetId(Long assetId) {
		this.assetId = assetId;
	}

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public String getAssetSerialNumber() {
		return assetSerialNumber;
	}

	public void setAssetSerialNumber(String assetSerialNumber) {
		this.assetSerialNumber = assetSerialNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public AssetFamily getFamily() {
		return family;
	}

	public void setFamily(AssetFamily family) {
		this.family = family;
	}

	@Override
	public String toString() {
		System.out.println(" ...... IN TOSTRING ....");
        JSONObject jsonInfo = new JSONObject();
        try {
			jsonInfo.put("assetName",this.getAssetName());
			jsonInfo.put("assetId",this.getAssetId());
			
	        if(this.getUser() != null){
	        	jsonInfo.put("user",this.getUser());
	        }

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return jsonInfo.toString();
	}
}
