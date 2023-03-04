package hiber.dao;

import hiber.model.*;
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
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   @SuppressWarnings("unchecked")
   public User getUserByCarModelAndSeries(String model,int series) {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User user where " +
              "user.car.series = :series " +
              "and user.car.model = :model");
      User user = query
              .setParameter("series",series)
              .setParameter("model", model)
              .getSingleResult();
      return user;
   }
}