package testdb.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import testdb.entity.School;
import testdb.utils.HibernateSessionFactory;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * Created by forpost on 13.03.17.
 */
public class SchoolDao {
    private Session session = HibernateSessionFactory.getSessionFactory().openSession();

    public List<School> getAllSchool() {
        try {
            Query query = session.createQuery("from School");

            List<School> schools = query.list();

            return schools;
        } catch (NoResultException e) {
            return null;
        }
    }


}
