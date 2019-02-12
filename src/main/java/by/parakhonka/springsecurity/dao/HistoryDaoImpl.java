package by.parakhonka.springsecurity.dao;

import by.parakhonka.springsecurity.entity.History;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("historyDao")
@Transactional
public class HistoryDaoImpl extends AbstractDao<Integer, History> implements IHistoryDao {

    public void save(History pHistory) {
        persist(pHistory);
    }

    public List<History> getAllHistoriUser(String pName) {
        Criteria crit = createEntityCriteria();

        List list = crit.add(Restrictions.eq("name", pName)).list();

        return list;
    }

    public History getByidHistory(int id) {
        Criteria crit = createEntityCriteria();

        History history = (History) crit.add(Restrictions.eq("id", id)).uniqueResult();
        return history;
    }

    public List<History> getTenHistory(int pPage, int pCount, String pUserName) {
        Criteria crit = createEntityCriteria();
        crit.addOrder(Order.desc("date"));

        if (pPage == 1) {
            return crit.setFirstResult(0).setMaxResults(pCount).list();
        } else {
//           return crit.add(Restrictions.between("date", pPage * 10, pPage * 10 + 9)).list();
            return crit.setFirstResult(pPage * pCount).setMaxResults(pCount).list();
        }

    }

    public History getLastHistory(String userName) {
        Criteria c = createEntityCriteria();
        c.addOrder(Order.desc("id"));
        c.setMaxResults(1);
        History history = (History) c.uniqueResult();
        System.out.println(history.getName());
        return (History) c.uniqueResult();
    }
}
