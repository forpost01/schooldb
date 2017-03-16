package schoolManager.service;

import org.apache.log4j.Logger;
import schoolManager.dao.ClassroomDao;
import schoolManager.dao.SchoolDao;
import schoolManager.entity.Classroom;
import schoolManager.entity.School;

import java.util.List;

/**
 * Created by forpost on 15.03.17.
 */
public class ClassroomService {

    private static ClassroomDao classroomDao;
    private static SchoolService schoolService;

    private static final Logger logger = Logger.getLogger(ClassroomService.class);

    public ClassroomService() {
        classroomDao = new ClassroomDao();
        schoolService = new SchoolService();
    }

    public void save(Classroom classroom) {
        School school = schoolService.findById(classroom.getSchool().getSchool_id());
        if (school == null) {
            logger.info("Atempt to save classroom whith wrong school: " + classroom);
        } else {
            logger.info("Classroom save - START");
            classroomDao.openCurrentSessionwithTransaction();
            classroomDao.save(classroom);
            classroomDao.closeCurrentSessionwithTransaction();
            logger.info("Classroom save - END:" + classroom);
        }
    }

    public void update(Classroom classroom) {
        logger.info("Classroom update - START");
        classroomDao.openCurrentSessionwithTransaction();
        classroomDao.update(classroom);
        classroomDao.closeCurrentSessionwithTransaction();
        logger.info("Classroom update - END:" + classroom);
    }

    public Classroom findById(int id) {
        Classroom classroom;
        if (id <= 0) {
            logger.info("attempt to find classroom with wrong ID:" + id);
            classroom = null;
        } else {
            logger.info("Classroom findById - START");
            classroomDao.openCurrentSession();
            classroom = classroomDao.findById(id);
            classroomDao.closeCurrentSession();
            logger.info("Classroom findById - END: id=" + id + " - " + classroom);
        }
        return classroom;
    }

    public Classroom findBySchoolAndName(int id, String name) {
        Classroom classroom;
        if (id <= 0) {
            logger.info("attempt to find classroom with wrong ID:" + id);
            classroom = null;
        } else {
            logger.info("Classroom findBySchoolAndName - START: school_id " + id + " classroom " + name);
            classroomDao.openCurrentSession();
            classroom = classroomDao.findBySchoolAndName(id, name);
            classroomDao.closeCurrentSession();
            logger.info("Classroom findBySchoolAndName - END: " + classroom);
        }
        return classroom;
    }

    public void delete(int id) {
        logger.info("Classroom delete - START");
        classroomDao.openCurrentSessionwithTransaction();
        Classroom classroom = classroomDao.findById(id);
        classroomDao.delete(classroom);
        classroomDao.closeCurrentSessionwithTransaction();
        logger.info("Classroom delete - END: " + classroom.getSchool().getSchool_id() + " " + classroom.getClassName());
    }

    public List<Classroom> findAll() {
        logger.info("Classroom findAll - START");
        classroomDao.openCurrentSession();
        List<Classroom> classrooms = classroomDao.findAll();
        classroomDao.closeCurrentSession();
        logger.info("Classroom findAll - END: found=" + (classrooms != null ? classrooms.size() : null));

        return classrooms;
    }

    public void deleteAll() {
        logger.info("Classroom deleteAll - START");
        classroomDao.openCurrentSessionwithTransaction();
        classroomDao.deleteAll();
        classroomDao.closeCurrentSessionwithTransaction();
        logger.info("Classroom deleteAll - END");

    }

    public ClassroomDao classroomDao() {
        return classroomDao;
    }
}
