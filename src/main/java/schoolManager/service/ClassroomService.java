package schoolManager.service;

import org.apache.log4j.Logger;
import schoolManager.dao.ClassroomDao;
import schoolManager.entity.Classroom;

import java.util.List;

/**
 * Created by forpost on 15.03.17.
 */
public class ClassroomService {

    private static ClassroomDao classroomDao;
    private static final Logger logger = Logger.getLogger(ClassroomService.class);

    public ClassroomService() {
        classroomDao = new ClassroomDao();
    }

    public void save(Classroom classroom) {
        logger.info("Classroom save - START");
        classroomDao.openCurrentSessionwithTransaction();
        classroomDao.save(classroom);
        classroomDao.closeCurrentSessionwithTransaction();
        logger.info("Classroom save - END:" +classroom);
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
            logger.info("Classroom findById - END: id=" +id +" - "+classroom);
        }
        return classroom;
    }

    public void delete(int id) {
        logger.info("Classroom delete - START");
        classroomDao.openCurrentSessionwithTransaction();
        Classroom classroom = classroomDao.findById(id);
        classroomDao.delete(classroom);
        classroomDao.closeCurrentSessionwithTransaction();
        logger.info("Classroom delete - END: "+classroom.getSchool().getSchool_id()+" "+classroom.getClassName());
    }

    public List<Classroom> findAll() {
        logger.info("Classroom findAll - START");
        classroomDao.openCurrentSession();
        List<Classroom> classrooms = classroomDao.findAll();
        classroomDao.closeCurrentSession();
        logger.info("Classroom findAll - END: found="+ (classrooms!=null? classrooms.size():null));

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
