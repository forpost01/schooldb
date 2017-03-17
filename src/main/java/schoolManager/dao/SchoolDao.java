package schoolManager.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import schoolManager.entity.School;

import static schoolManager.utils.HibernateSessionFactory.getSessionFactory;

//static import schoolManager.utils.HibernateSessionFactory;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by forpost on 13.03.17.
 */
public class SchoolDao {

    private Session currentSession;

    private Transaction currentTransaction;

    public Session openCurrentSession() {
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }

    public Session openCurrentSessionwithTransaction() {
        currentSession = getSessionFactory().openSession();
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
