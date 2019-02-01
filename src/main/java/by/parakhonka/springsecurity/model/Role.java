package by.parakhonka.springsecurity.model;

public enum Role {
	USER("USER");
	String userProfileType;
	
	private Role(String userProfileType){
		this.userProfileType = userProfileType;
	}
	
	public String getUserProfileType(){
		return userProfileType;
	}
	
}
