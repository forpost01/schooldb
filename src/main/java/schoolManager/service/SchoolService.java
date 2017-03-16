package schoolManager.service;

import org.apache.log4j.Logger;
import schoolManager.dao.SchoolDao;
import schoolManager.entity.School;

import java.util.List;

/**
 * Created by forpost on 15.03.17.
 */
public class SchoolService {

    private static SchoolDao schoolDao;
    private static final Logger logger = Logger.getLogger(SchoolService.class);

    public SchoolService() {
        schoolDao = new SchoolDao();
    }

    public void save(School school) {
        logger.info("save - START");
        schoolDao.openCurrentSessionwithTransaction();
        schoolDao.save(school);
        schoolDao.closeCurrentSessionwithTransaction();
        logger.info("save - END:" +school);
    }

    public void update(School school) {
        logger.info("update - START");
        schoolDao.openCurrentSessionwithTransaction();
        schoolDao.update(school);
        schoolDao.closeCurrentSessionwithTransaction();
        logger.info("update - END:" + school);
    }

    public School findById(int id) {
        School school;
        if (id <= 0) {
            logger.info("attempt to find school with wrong ID:" + id);
            school = null;
        } else {
            logger.info("findById - START");
            schoolDao.openCurrentSession();
            school = schoolDao.findById(id);
            schoolDao.closeCurrentSession();
            logger.info("findById - END: id=" +id +" - "+school);
        }
        return school;
    }

    public void delete(int id) {
        logger.info("delete - START");
        schoolDao.openCurrentSessionwithTransaction();
        School school = schoolDao.findById(id);
        schoolDao.delete(school);
        schoolDao.closeCurrentSessionwithTransaction();
        logger.info("delete - END: "+school.getSchool_id());
    }

    public List<School> findAll() {
        logger.info("findAll - START");
        schoolDao.openCurrentSession();
        List<School> schools = schoolDao.findAll();
        schoolDao.closeCurrentSession();
        logger.info("findAll - END: found="+ (schools!=null? schools.size():null));

        return schools;
    }

    public void deleteAll() {
        logger.info("deleteAll - START");
        schoolDao.openCurrentSessionwithTransaction();
        schoolDao.deleteAll();
        schoolDao.closeCurrentSessionwithTransaction();
        logger.info("deleteAll - END");

    }

    public SchoolDao schoolDao() {
        return schoolDao;
    }
}
