package by.parakhonka.springsecurity.dao;

import by.parakhonka.springsecurity.model.User;

public interface IUserDao {

	void save(User user);
	
	User findById(int id);
	
	User findBySSO(String sso);
	
}

