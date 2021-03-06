package ru.stqa.pft.mantis.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.mantis.model.User;

import java.util.List;

/**
 * Created by Даниил on 11.06.2017.
 */
public class DbHelper {
  private final SessionFactory sessionFactory;

  public DbHelper() {

    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();

    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();


  }


  public User getUser() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<User> result = session.createQuery("from User").list();

    session.getTransaction().commit();
    session.close();
    return result.stream().filter((s)->(!s.getUsername().equals("administrator"))).iterator().next();
  }
}


