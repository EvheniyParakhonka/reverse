package by.parakhonka.springsecurity.service;

import by.parakhonka.springsecurity.model.User;

public interface UserService {

	void save(User user);
	
	User findById(int id);
	
	User findBySso(String sso);
	
}