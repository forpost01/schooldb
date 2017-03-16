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
        school.setAddress("Nikolaev, Parkovaya 1");
        school.setFioDirector("Boris Britva");
        schoolService.save(school);
        School school1 = schoolService.findById(81);
        assertTrue(school.equals(school1));
    }

    @Test
    public void update() throws Exception {
        School school = schoolService.findById(45);
        school.setAccountNumber("1111111111");
        schoolService.update(school);
        assertEquals("1111111111",schoolService.findById(45).getAccountNumber());
    }

    @Test
    public void findById() throws Exception {
        School school = schoolService.findById(51);
        assertEquals("Geroev 17",school.getAddress());
        assertEquals("Cидоров П.",school.getFioDirector());
        assertEquals("123123123945",school.getAccountNumber());
    }

    @Test
    public void delete() throws Exception {
        assertTrue(schoolService.findById(12) != null);
        schoolService.delete(12);
        assertEquals(null,schoolService.findById(12));
    }

    @Test
    public void findAll() throws Exception {
        List<School> schools = schoolService.findAll();
        assertTrue(schools.size()>0);
    }

    @Ignore
    @Test
    public void deleteAll() throws Exception {
        assertTrue(schoolService.findAll() != null);
        schoolService.deleteAll();
        assertTrue(schoolService.findAll() == null);
    }

}