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

    public void save(School school) {
        logger.info("School save - START");
        schoolDao.openCurrentSessionwithTransaction();
        schoolDao.save(school);
        schoolDao.closeCurrentSessionwithTransaction();
        logger.info("School save - END:" +school);
    }

    public void update(School school) {
        logger.info("School update - START");
        schoolDao.openCurrentSessionwithTransaction();
        schoolDao.update(school);
        schoolDao.closeCurrentSessionwithTransaction();
        logger.info("School update - END:" + school);
    }

    public School findById(int id) {
        School school = null;
        if (id <= 0) {
            logger.info("attempt to find school with wrong ID:" + id);
        } else {
            logger.info("School findById - START");
            schoolDao.openCurrentSession();
            try {
                school = schoolDao.findById(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            schoolDao.closeCurrentSession();
            logger.info("School findById - END: id=" +id +" - "+(school!=null? school:null));
        }
        return school;
    }

    public void delete(int id) {
        logger.info("School delete - START");
        schoolDao.openCurrentSessionwithTransaction();
        School school = null;
        try {
            school = schoolDao.findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        schoolDao.delete(school);
        schoolDao.closeCurrentSessionwithTransaction();
        logger.info("School delete - END: "+school.getSchool_id());
    }

    public List<School> findAll() {
        logger.info("School findAll - START");
        schoolDao.openCurrentSession();
        List<School> schools = schoolDao.findAll();
        schoolDao.closeCurrentSession();
        logger.info("School findAll - END: found="+ (schools!=null? schools.size():null));

        return schools;
    }

    public void deleteAll() {
        logger.info("School deleteAll - START");
        schoolDao.openCurrentSessionwithTransaction();
        schoolDao.deleteAll();
        schoolDao.closeCurrentSessionwithTransaction();
        logger.info("School deleteAll - END");

    }

    public SchoolDao schoolDao() {
        return schoolDao;
    }
}
