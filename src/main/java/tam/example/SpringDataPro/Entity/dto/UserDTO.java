package tam.example.SpringDataPro.Entity.dto;

import tam.example.SpringDataPro.Entity.User;

public class UserDTO {
//Long userId;
	
	String userName;
	
	private User manager;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}
	
}
