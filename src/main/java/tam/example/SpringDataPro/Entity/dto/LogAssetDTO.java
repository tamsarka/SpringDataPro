package tam.example.SpringDataPro.Entity.dto;

public class LogAssetDTO extends AssetDTO{
	boolean logProperties;
	boolean logPropertiesSecond;
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
	
	
	
}
