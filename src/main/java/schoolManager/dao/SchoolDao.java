package schoolManager.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import schoolManager.entity.School;

import schoolManager.utils.HibernateSessionFactory;

//static import schoolManager.utils.HibernateSessionFactory;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by forpost on 13.03.17.
 */
public class SchoolDao {
    //private HibernateSessionFactory hibernateSessionFactory;

    public SchoolDao(){
       // hibernateSessionFactory = new HibernateSessionFactory();
    }
    private Session currentSession;

    private Transaction currentTransaction;

    public Session openCurrentSession() {
        currentSession = HibernateSessionFactory.getSessionFactory().openSession();
        return currentSession;
    }

    public Session openCurrentSessionwithTransaction() {
        currentSession = HibernateSessionFactory.getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSession() {
        currentSession.close();
    }

    public void closeCurrentSessionwithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

    public void save(School school) {
        getCurrentSession().save(school);
    }

    public void update(School school) {
        getCurrentSession().update(school);
    }

    public School findById(int id) {
        School school = getCurrentSession().get(School.class, id);
        return school;
    }

    public void delete(School school) {
        getCurrentSession().delete(school);
    }

    @SuppressWarnings("unchecked")
    public List<School> findAll() {
        List<School> schools = getCurrentSession().createQuery("from School").list();
        if (schools.size()==0) schools = null;
        return schools;
    }

    public void deleteAll() {
        List<School> schools = findAll();
        for (School school : schools) {
            delete(school);
        }
    }

}
