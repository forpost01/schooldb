package schoolManager.service;

import org.apache.log4j.Logger;
import schoolManager.dao.ClassroomDao;
import schoolManager.entity.Classroom;
import schoolManager.entity.School;

import javax.persistence.NoResultException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by forpost on 15.03.17.
 */
@Path("/class")
public class ClassroomService {

    private static ClassroomDao classroomDao;
    private static SchoolService schoolService;

    private static final Logger logger = Logger.getLogger(ClassroomService.class);

    public ClassroomService() {
        classroomDao = new ClassroomDao();
        schoolService = new SchoolService();
    }

    @POST
    @Path("/post")
    @Consumes(MediaType.APPLICATION_JSON + "; charset=UTF-8")
    public Response saveClassroom(Classroom classroom) {
        System.out.println(classroom);
        logger.info("Attempt to save classroom : " + classroom);
        boolean status = save(classroom);
        int code = status ? 201 : 409;
        String result = "Classroom save : " + status;
        return Response.status(code).entity(result).build();
    }

    public boolean save(Classroom classroom) {
        School school = schoolService.findById(classroom.getSchool());
        if (school == null) {
            logger.info("Attempt to save classroom with wrong school: " + classroom);
            return false;
        }
        //check if classroom already exist in base
        int school_id = classroom.getSchool();
        String classroomName = classroom.getClassName();
        Classroom classroom1 = findBySchoolAndName(school_id, classroomName);
        if (null != classroom1) {
            logger.info("Classroom save - BAD: already in DB " + classroom);
            return false;
        }

        logger.info("Classroom save - START");
        classroomDao.openCurrentSessionwithTransaction();
        classroomDao.save(classroom);
        try {
            classroomDao.closeCurrentSessionwithTransaction();
        } catch (Exception e) {
            logger.info("Classroom save - BAD:" + classroom);
            e.printStackTrace();
            return false;
        }
        logger.info("Classroom save - END:" + classroom);
        return true;

    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON + "; charset=UTF-8")
    @Path("/put")
    public Response updateClassroom(Classroom classroom) {
        boolean status = update(classroom);
        int code = status ? 200 : 404;
        String result = "Classroom update : " + status;
        return Response.status(code).entity(result).build();
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

    @GET
    @Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
    public Classroom findById(@QueryParam("id") int id) {
        Classroom classroom = null;
        if (id <= 0) {
            logger.info("Attempt to find classroom with wrong ID:" + id);
        } else {
            logger.info("Classroom findById - START");
            classroomDao.openCurrentSession();
            try {
                classroom = classroomDao.findById(id);
            } catch (Exception e) {
                logger.info("Classroom findById - BAD: not in DB");
            } finally {
                classroomDao.closeCurrentSession();
            }

            logger.info("Classroom findById - END: id=" + id + " - " + classroom);
        }
        return classroom;
    }

    public Classroom findBySchoolAndName(int id, String name) {
        Classroom classroom = null;
        if (id <= 0) {
            logger.info("attempt to find classroom with wrong ID:" + id);
        } else {
            logger.info("Classroom findBySchoolAndName - START: school_id " + id + " classroom " + name);
            classroomDao.openCurrentSession();
            try {
                classroom = classroomDao.findBySchoolAndName(id, name);
            } catch (NoResultException e) {
                //e.printStackTrace();
            } finally {
                classroomDao.closeCurrentSession();
                logger.info("Classroom findBySchoolAndName - END: " + classroom);
            }
        }
        return classroom;
    }

    @DELETE
    @Path("/delete/{id: \\d+}")
    public Response deleteClassroom(@PathParam("id") int id) {
        boolean status = delete(id);
        int code = status ? 200 : 204;
        String result = "Classroom delete : " + status;
        return Response.status(code).entity(result).build();
    }

    public boolean delete(int id) {
        logger.info("Classroom delete - START");
        classroomDao.openCurrentSessionwithTransaction();
        Classroom classroom = null;
        try {
            classroom = classroomDao.findById(id);
        } catch (Exception e) {
            logger.info("Classroom delete - BAD: not in DB");
            //classroomDao.closeCurrentSessionwithTransaction();
            return false;
            //e.printStackTrace();
        }
        if (classroom != null) {
            classroomDao.delete(classroom);
            try {
                classroomDao.closeCurrentSessionwithTransaction();
            } catch (Exception e) {
                logger.info("Classroom delete - BAD: " + classroom);
                e.printStackTrace();
                return false;
            }
            logger.info("Classroom delete - END: " + classroom.getSchool() + " " + classroom.getClassName());
            return true;
        } else {
            logger.info("Classroom delete - BAD: not in DB");
            return false;
        }
    }

    @GET
    @Path("/all")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + "; charset=UTF-8")
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON + "; charset=UTF-8")
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
