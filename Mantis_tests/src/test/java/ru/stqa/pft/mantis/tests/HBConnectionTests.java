package ru.stqa.pft.mantis.tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import ru.stqa.pft.mantis.model.User;

import java.util.List;

/**
 * Created by Даниил on 11.06.2017.
 */
public class HBConnectionTests {
  private SessionFactory sessionFactory;

  @BeforeClass
  protected void setUp() throws Exception {
    // A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
    try {
      sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
    }
    catch (Exception e) {
      // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
      // so destroy it manually.
      e.printStackTrace();
      StandardServiceRegistryBuilder.destroy( registry );
    }

  }


 @Test
 public void testHbConnection() {
   Session session = sessionFactory.openSession();
   session.beginTransaction();
   List<User> result = session.createQuery("from User").list();
   for (User user : result) {
     System.out.println(user);

   }
   session.getTransaction().commit();
   session.close();

 }
}
