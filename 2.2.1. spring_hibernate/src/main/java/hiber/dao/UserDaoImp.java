package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession()
                .createQuery("SELECT u FROM User u JOIN FETCH u.car", User.class);
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getUsersByCarModelAndSeries(String carModel, int series) {
        String queryString = "SELECT u FROM User u JOIN FETCH u.car c WHERE c.model = :carModel AND c.series = :series";
        return sessionFactory.getCurrentSession()
                .createQuery(queryString, User.class)
                .setParameter("carModel", carModel)
                .setParameter("series", series)
                .getResultList();
    }
}
