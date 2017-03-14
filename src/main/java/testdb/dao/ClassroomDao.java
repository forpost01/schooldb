package testdb.dao;

import org.hibernate.Session;
import testdb.utils.HibernateSessionFactory;

/**
 * Created by forpost on 13.03.17.
 */
public class ClassroomDao {
    private Session session = HibernateSessionFactory.getSessionFactory().openSession();

}
