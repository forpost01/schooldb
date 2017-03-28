package schoolManager.service;

import org.junit.Ignore;
import org.junit.Test;
import schoolManager.entity.Classroom;
import schoolManager.entity.School;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by forpost on 16.03.17.
 */
//@Ignore
public class ClassroomServiceTest {

    public SchoolService schoolService = new SchoolService();
    public ClassroomService classroomService = new ClassroomService();


    @Test
    public void save() throws Exception {
        Classroom classroom = new Classroom("1А",21,"Иванова Маргарита",schoolService.findById(45));
        assertTrue(classroomService.save(classroom));
        Classroom classroom1 = classroomService.findBySchoolAndName(45,"1А");
        assertTrue(classroom.equals(classroom1));
    }

    @Test
    public void saveWithoutSchool() throws Exception {
        School school = new School("address","fio","account");
        Classroom classroom = new Classroom("7А",21,"Иванова Маргарита",school);
        assertFalse(classroomService.save(classroom));
    }

    @Test
    public void update() throws Exception {
        Classroom classroom = classroomService.findById(1);
        classroom.setNumberPupil(99);
        assertTrue(classroomService.update(classroom));
        classroom = classroomService.findById(1);
        assertEquals(99,classroom.getNumberPupil());
    }

    @Test
    public void updateBad() throws Exception {
        School school = new School("address","fio","account");
        Classroom classroom = classroomService.findById(1);
        int currentPupil = classroom.getNumberPupil();
        classroom.setSchool(school);
        classroom.setNumberPupil(99);
        assertFalse(classroomService.update(classroom));
        classroom = classroomService.findById(1);
        assertEquals(currentPupil,classroom.getNumberPupil());
    }

    @Test
    public void findById() throws Exception {
        assertNotNull(classroomService.findById(1));
    }

    @Test
    public void findByWrongId() throws Exception {
        assertNull(classroomService.findById(134));
    }

    @Test
    public void delete() throws Exception {
        Classroom classroom = classroomService.findById(2);
        assertNotNull(classroom);
        assertTrue(classroomService.delete(classroom.getId()));
        assertNull(classroomService.findById(2));
    }

    @Test
    public void deleteWithBadId() throws Exception {
        assertFalse(classroomService.delete(999));
    }

    @Test
    public void findAll() throws Exception {
        List<Classroom> classrooms = classroomService.findAll();
        assertTrue(classrooms.size()>0);
    }

    @Test
    public void findBySchoolAndName() throws Exception {
        Classroom classroom = classroomService.findById(1);
        Classroom classroom1 = classroomService.findBySchoolAndName(classroom.getSchool()
                ,classroom.getClassName());
        assertTrue(classroom.equals(classroom1));
    }

    @Test
    public void findBySchoolAndNameBad() throws Exception {
        assertNull(classroomService.findBySchoolAndName(12,"1A1"));
    }

    @Ignore
    @Test
    public void deleteAll() throws Exception {
        assertTrue(classroomService.deleteAll());
        assertNull(classroomService.findById(1));
    }

}