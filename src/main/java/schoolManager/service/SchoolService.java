package schoolManager.service;

import org.apache.log4j.Logger;
import schoolManager.dao.SchoolDao;
import schoolManager.entity.School;

import java.sql.SQLException;
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

    public boolean save(School school) {
        logger.info("School save - START");
        schoolDao.openCurrentSessionwithTransaction();
        schoolDao.save(school);
        try {
            schoolDao.closeCurrentSessionwithTransaction();
        } catch (Exception e) {
            logger.info("School save - BAD:" + school);
            e.printStackTrace();
            return false;
        }
        logger.info("School save - END:" +school);
        return true;
    }

    public boolean update(School school) {
        logger.info("School update - START");
        schoolDao.openCurrentSessionwithTransaction();
        schoolDao.update(school);
        try {
            schoolDao.closeCurrentSessionwithTransaction();
        } catch (Exception e) {
            logger.info("School update - BAD:" + school);
            e.printStackTrace();
            return false;
        }
        logger.info("School update - END:" + school);
        return true;
    }

    public School findById(int id) {
        School school = null;
        if (id <= 0) {
            logger.info("Attempt to find school with wrong ID:" + id);
        } else {
            logger.info("School findById - START");
            schoolDao.openCurrentSession();
            school = schoolDao.findById(id);
            schoolDao.closeCurrentSession();
            logger.info("School findById - END: id=" +id +" - "+(school!=null? school:null));
        }
        return school;
    }

    public boolean delete(int id) {
        logger.info("School delete - START");
        schoolDao.openCurrentSessionwithTransaction();
        School school = schoolDao.findById(id);
        if (school!=null) {
            schoolDao.delete(school);
            try {
                schoolDao.closeCurrentSessionwithTransaction();
            } catch (Exception e) {
                logger.info("School delete - BAD: " + school);
                e.printStackTrace();
                return false;
            }
            logger.info("School delete - END: " + school.getSchool_id());
            return true;
        } else {
            logger.info("School delete - BAD: not in DB");
            return false;
        }
    }

    public List<School> findAll() {
        logger.info("School findAll - START");
        schoolDao.openCurrentSession();
        List<School> schools = schoolDao.findAll();
        schoolDao.closeCurrentSession();
        logger.info("School findAll - END: found="+ (schools!=null? schools.size():null));

        return schools;
    }

    public boolean deleteAll() {
        logger.info("School deleteAll - START");
        schoolDao.openCurrentSessionwithTransaction();
        schoolDao.deleteAll();
        try {
            schoolDao.closeCurrentSessionwithTransaction();
        } catch (Exception e) {
            logger.info("School deleteAll - BAD: ");
            e.printStackTrace();
            return false;
        }
        logger.info("School deleteAll - END: ");
        return true;
    }

    public SchoolDao schoolDao() {
        return schoolDao;
    }
}
