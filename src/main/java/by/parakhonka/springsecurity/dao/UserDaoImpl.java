package by.parakhonka.springsecurity.dao;

import by.parakhonka.springsecurity.model.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("userDao")
@Transactional
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

	public void save(User user) {
		persist(user);
	}
	
	public User findById(int id) {
		return getByKey(id);
	}

	public User findBySSO(String sso) {
		System.out.println("find by sso" + sso);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("username", sso));
		return (User) crit.uniqueResult();
	}

}
