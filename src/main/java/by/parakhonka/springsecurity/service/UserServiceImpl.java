package by.parakhonka.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.parakhonka.springsecurity.dao.IUserDao;
import by.parakhonka.springsecurity.model.User;

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao dao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	
	public void save(User user){
		System.out.println("save user " + user.getUsername() + " " + user.getPassword());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		System.out.println("password whit encoding " + user.getPassword());
		dao.save(user);
	}
	
	public User findById(int id) {
		return dao.findById(id);
	}

	public User findBySso(String sso) {
		System.out.println(sso);
		User user = dao.findBySSO(sso);
		System.out.println(user.getUsername());
		return user;
	}
	
}
