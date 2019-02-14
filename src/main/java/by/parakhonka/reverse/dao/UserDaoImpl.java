package by.parakhonka.reverse.dao;

import by.parakhonka.reverse.entity.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @see IUserDao
 */
@Repository("userDao")
@Transactional
public class UserDaoImpl extends AbstractDao<Integer, User> implements IUserDao {

    public void save(User pUser) {
        persist(pUser);
    }

    public User findById(int pId) {
        return getByKey(pId);
    }

    public User findByUserName(String pUserName) {
        System.out.println("find by user name" + pUserName);
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("username", pUserName));
        return (User) crit.uniqueResult();
    }

}
