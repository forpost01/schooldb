package schoolManager.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import schoolManager.entity.Classroom;

import java.util.List;

import static schoolManager.utils.HibernateSessionFactory.getSessionFactory;

/**
 * Created by forpost on 13.03.17.
 */
public class ClassroomDao {

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

    public void save(Classroom classroom) {
        getCurrentSession().save(classroom);
    }

    public void update(Classroom classroom) {
        getCurrentSession().update(classroom);
    }

    public Classroom findById(int id) {
        Classroom classroom = getCurrentSession().get(Classroom.class, id);
        return classroom;
    }

    public void delete(Classroom classroom) {
        getCurrentSession().delete(classroom);
    }

    @SuppressWarnings("unchecked")
    public List<Classroom> findAll() {
        List<Classroom> classrooms = getCurrentSession().createQuery("from Classroom").list();
        return classrooms;
    }

    public void deleteAll() {
        List<Classroom> classrooms = findAll();
        for (Classroom classroom : classrooms) {
            delete(classroom);
        }
    }
}
