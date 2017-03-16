package schoolManager.service;

import org.junit.Ignore;
import org.junit.Test;
import schoolManager.entity.Classroom;

import static org.junit.Assert.*;

/**
 * Created by forpost on 16.03.17.
 */
public class ClassroomServiceTest {
    public SchoolService schoolService = new SchoolService();
    public ClassroomService classroomService = new ClassroomService();

    @Ignore
    @Test
    public void save() throws Exception {
        Classroom classroom = new Classroom("1А",21,"Иванова Маргарита",schoolService.findById(45));
        classroomService.save(classroom);
        Classroom classroom1 = classroomService.findById(81);
        assertTrue(classroom.equals(classroom1));
    }

    @Test
    public void update() throws Exception {

    }

    @Test
    public void findById() throws Exception {

    }

    @Test
    public void delete() throws Exception {

    }

    @Test
    public void findAll() throws Exception {

    }

    @Test
    public void deleteAll() throws Exception {

    }

    @Test
    public void classroomDao() throws Exception {

    }

}