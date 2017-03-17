package schoolManager.service;

import org.junit.Ignore;
import org.junit.Test;
import schoolManager.entity.School;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by forpost on 16.03.17.
 */
public class SchoolServiceTest {
    public SchoolService schoolService = new SchoolService();
    @Test
    public void save() throws Exception {
        School school = new School();
        school.setSchool_id(81);
        school.setAccountNumber("234324234234");
        school.setAddress("Николаев, Парковая 1");
        school.setFioDirector("Васечкин Денис");
        schoolService.save(school);
        School school1 = schoolService.findById(81);
        assertTrue(school.equals(school1));
    }

    @Test
    public void update() throws Exception {
        School school = schoolService.findById(45);
        school.setAccountNumber("1111111111");
        assertTrue(schoolService.update(school));
        assertEquals("1111111111",schoolService.findById(45).getAccountNumber());
    }

    @Test
    public void findById() throws Exception {
        School school = schoolService.findById(51);
        assertEquals("Героев Украины 17",school.getAddress());
        assertEquals("Cидоров П.",school.getFioDirector());
        assertEquals("123123123945",school.getAccountNumber());
    }

    @Test
    public void findByIdBad() throws Exception {
        School school = schoolService.findById(151);
        assertNull(school);
    }

    @Test
    public void delete() throws Exception {
        assertNotNull(schoolService.findById(12));
        schoolService.delete(12);
        assertNull(schoolService.findById(12));
    }

    @Test
    public void deleteWithWrongId() throws Exception {
        assertNull(schoolService.findById(99));
        assertFalse(schoolService.delete(99));
    }

    @Test
    public void findAll() throws Exception {
        List<School> schools = schoolService.findAll();
        assertTrue(schools.size()>0);
    }

    @Ignore
    @Test
    public void deleteAll() throws Exception {
        assertNotNull(schoolService.findAll());
        schoolService.deleteAll();
        assertNull(schoolService.findAll());
    }

}