package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public List<User> getUsersByCarModelAndSeries(String carModel, int series) {
        String queryString = "SELECT u FROM User u WHERE u.car.model = :carModel AND u.car.series = :series";
        return sessionFactory.getCurrentSession()
                .createQuery(queryString, User.class)
                .setParameter("carModel", carModel)
                .setParameter("series", series).getResultList();
    }


}
