package by.parakhonka.reverse.dao;

import by.parakhonka.reverse.entity.History;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @see IHistoryDao
 */
@Repository("historyDao")
@Transactional
public class HistoryDaoImpl extends AbstractDao<Integer, History> implements IHistoryDao {

    public void save(History pHistory) {
        persist(pHistory);
    }

    public List<History> getAllHistoryUser(String pUserName) {
        Criteria crit = createEntityCriteria();
        return crit.add(Restrictions.eq("name", pUserName)).list();
    }

    public History getByIdHistory(int pId) {
        Criteria crit = createEntityCriteria();
        return (History) crit.add(Restrictions.eq("id", pId)).uniqueResult();
    }

    public List<History> getHistoryForOnePage(int pPage, int pCount, String pUserName) {
        Criteria crit = createEntityCriteria();
        crit.addOrder(Order.desc("date"));

        if (pPage == 1) {
            return crit.setFirstResult(0).setMaxResults(pCount).list();
        } else {
            return crit.setFirstResult(pPage * pCount).setMaxResults(pCount).list();
        }
    }

    public History getLastHistory(String pUserName) {
        Criteria c = createEntityCriteria();
        c.addOrder(Order.desc("id"));
        c.setMaxResults(1);
        History history = (History) c.uniqueResult();
        System.out.println(history.getName());
        return (History) c.uniqueResult();
    }
}
