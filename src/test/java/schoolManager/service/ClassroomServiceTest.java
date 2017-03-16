package schoolManager.service;

import org.junit.Ignore;
import org.junit.Test;
import schoolManager.entity.Classroom;
import schoolManager.entity.School;

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
        Classroom classroom1 = classroomService.findBySchoolAndName(45,"1А");
        assertTrue(classroom.equals(classroom1));
    }

    @Test
    public void saveWithoutSchool() throws Exception {
        School school = new School("address","fio","account");
        Classroom classroom = new Classroom("7А",21,"Иванова Маргарита",school);
        classroomService.save(classroom);
//        Classroom classroom1 = classroomService.findBySchoolAndName(45,"1А");
//        assertTrue(classroom.equals(classroom1));
    }

    @Test
    public void update() throws Exception {
        Classroom classroom = classroomService.findById(1);
        classroom.setNumberPupil(99);
        classroomService.update(classroom);
        classroom = classroomService.findById(1);
        assertEquals(99,classroom.getNumberPupil());
    }

    @Test
    public void updateBad() throws Exception {
        Classroom classroom = classroomService.findById(1);
        classroom.setNumberPupil(99);
        classroomService.update(classroom);
        classroom = classroomService.findById(1);
        assertEquals(99,classroom.getNumberPupil());
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
    public void findBySchoolAndName() throws Exception {
        Classroom classroom = classroomService.findById(1);
        Classroom classroom1 = classroomService.findBySchoolAndName(classroom.getSchool().getSchool_id()
                ,classroom.getClassName());
        assertTrue(classroom.equals(classroom1));
    }
    @Test
    public void deleteAll() throws Exception {

    }

    @Test
    public void classroomDao() throws Exception {

    }

}