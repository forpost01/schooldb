package schoolManager.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import schoolManager.entity.Classroom;

import java.sql.SQLException;
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

    public void closeCurrentSessionwithTransaction() throws Exception {
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

    public Classroom findBySchoolAndName(int id,String name) throws javax.persistence.NoResultException{
        Classroom classroom = (Classroom) getCurrentSession()
                .createQuery("select e from Classroom e where e.className=:name AND e.school.school_id=:id")
                .setParameter("name",name)
                .setParameter("id",id)
                .getSingleResult();
        return classroom;
    }

    public void delete(Classroom classroom) {
        if (classroom!=null) getCurrentSession().delete(classroom);
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
