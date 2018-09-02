package tam.example.SpringDataPro.Entity;

import javax.persistence.Entity;

import org.json.JSONException;
import org.json.JSONObject;

@Entity
public class LogAsset extends Asset{
	
	
	
	boolean logProperties;
	boolean logPropertiesSecond;

	public LogAsset() {
		// TODO Auto-generated constructor stub
	}

	public LogAsset(String assetName, User user, boolean logProperties, boolean logPropertiesSecond, String assetSerialNumber, String description, AssetFamily family) {
		super(assetName, assetSerialNumber, description, user, family);
		this.logProperties = logProperties;
		this.logPropertiesSecond = logPropertiesSecond;
	}
	public boolean isLogProperties() {
		return logProperties;
	}

	public void setLogProperties(boolean logProperties) {
		this.logProperties = logProperties;
	}

	public boolean isLogPropertiesSecond() {
		return logPropertiesSecond;
	}

	public void setLogPropertiesSecond(boolean logPropertiesSecond) {
		this.logPropertiesSecond = logPropertiesSecond;
	}

	@Override
	public String toString() {
		System.out.println(" ...... IN TOSTRING LOG....");
        JSONObject jsonInfo = new JSONObject();
        try {
//			jsonInfo.put("assetName",this.getAssetName());
			jsonInfo.put("logPropertiesSecond",this.isLogPropertiesSecond());
			jsonInfo.put("logProperties",this.isLogProperties());

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return jsonInfo.toString();
	}
	
	
	
}
