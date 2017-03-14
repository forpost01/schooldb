import org.hibernate.Session;
import org.junit.Test;
import testdb.dao.SchoolDao;
import testdb.entity.Classroom;
import testdb.entity.School;
import testdb.utils.HibernateSessionFactory;

import java.util.List;

/**
 * Created by forpost on 14.03.17.
 */
public class schoolDaoTest {
    @Test
    public void testAddSchool() throws Exception {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        session.beginTransaction();

        School school = new School();

        school.setSchool_id(5);
        school.setAccountNumber("234324234234");
        school.setAddress("Nikolaev, Parkovaya 1");
        school.setFioDirector("Boris Britva");

        session.save(school);
   //     session.getTransaction().commit();

        Classroom classroom = new Classroom();
        classroom.setClassName("6a");
        classroom.setNumberPupil(31);
        classroom.setSchool(school);
        classroom.setFioTeacher("Maria Vasilievna");
        session.save(classroom);
        Classroom classroom1 = new Classroom();
        classroom1.setClassName("6b");
        classroom1.setNumberPupil(31);
        classroom1.setSchool(school);
        classroom1.setFioTeacher("Maria Vasilievna");
        session.save(classroom1);
        session.getTransaction().commit();

        session.close();
    }

    @Test
    public void testgetAllSchool() throws Exception {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        session.beginTransaction();
        SchoolDao schoolDao = new SchoolDao();
        List<School> schools = schoolDao.getAllSchool();
        for (School school:schools) {
            System.out.println(school);
        }
        session.close();
    }
}
