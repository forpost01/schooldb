package schoolManager.service;

import org.apache.log4j.Logger;
import schoolManager.dao.ClassroomDao;
import schoolManager.dao.SchoolDao;
import schoolManager.entity.Classroom;
import schoolManager.entity.School;

import javax.persistence.PersistenceException;
import java.sql.SQLException;
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

    public boolean save(Classroom classroom) {
        School school = schoolService.findById(classroom.getSchool().getSchool_id());
        if (school == null) {
            logger.info("Attempt to save classroom with wrong school: " + classroom);
            return false;
        } else {
            logger.info("Classroom save - START");
            classroomDao.openCurrentSessionwithTransaction();
            classroomDao.save(classroom);
            try {
                classroomDao.closeCurrentSessionwithTransaction();
            } catch (Exception e) {
                logger.info("Classroom save - BAD:" + classroom);
                e.printStackTrace();
            }
            logger.info("Classroom save - END:" + classroom);
        }
        return true;
    }

    public boolean update(Classroom classroom) {
        logger.info("Classroom update - START");
        classroomDao.openCurrentSessionwithTransaction();
        classroomDao.update(classroom);
        try {
            classroomDao.closeCurrentSessionwithTransaction();
        } catch (Exception e) {
            logger.info("Classroom update - BAD:" + classroom);
            logger.debug(e);
            return false;
        }
        logger.info("Classroom update - END:" + classroom);
        return true;
    }

    public Classroom findById(int id) {
        Classroom classroom;
        if (id <= 0) {
            logger.info("Attempt to find classroom with wrong ID:" + id);
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

    public boolean delete(int id) {
        logger.info("Classroom delete - START");
        classroomDao.openCurrentSessionwithTransaction();
        Classroom classroom = classroomDao.findById(id);
        if (classroom!=null) {
            classroomDao.delete(classroom);
            try {
                classroomDao.closeCurrentSessionwithTransaction();
            } catch (Exception e) {
                logger.info("Classroom delete - BAD: " + classroom);
                e.printStackTrace();
                return false;
            }
            logger.info("Classroom delete - END: " + classroom.getSchool().getSchool_id() + " " + classroom.getClassName());
            return true;
        } else {
            logger.info("Classroom delete - BAD: not in DB");
            return false;
        }
    }

    public List<Classroom> findAll() {
        logger.info("Classroom findAll - START");
        classroomDao.openCurrentSession();
        List<Classroom> classrooms = classroomDao.findAll();
        classroomDao.closeCurrentSession();
        logger.info("Classroom findAll - END: found=" + (classrooms != null ? classrooms.size() : null));

        return classrooms;
    }

    public boolean deleteAll() {
        logger.info("Classroom deleteAll - START");
        classroomDao.openCurrentSessionwithTransaction();
        classroomDao.deleteAll();
        try {
            classroomDao.closeCurrentSessionwithTransaction();
        } catch (Exception e) {
            logger.info("Classroom deleteAll - BAD: ");
            e.printStackTrace();
            return false;
        }
        logger.info("Classroom deleteAll - END");
        return true;
    }

    public ClassroomDao classroomDao() {
        return classroomDao;
    }
}
