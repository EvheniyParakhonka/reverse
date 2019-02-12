package by.parakhonka.springsecurity.service;

import by.parakhonka.springsecurity.entity.User;

public interface IUserService {

	void save(User user);
	
	User findById(int id);
	
	User findBySso(String sso);
	
}