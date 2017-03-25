package schoolManager.service;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import schoolManager.dao.SchoolDao;
import schoolManager.entity.Classroom;
import schoolManager.entity.School;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by forpost on 15.03.17.
 */
@Path("/schools")
public class SchoolService {

    private SchoolDao schoolDao;
    private static final Logger logger = Logger.getLogger(SchoolService.class);

    public SchoolService() {
        schoolDao = new SchoolDao();
    }

    @POST
    @Path("/post")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveSchool(School school) {
        boolean status = save(school);
        int code = status ? 201 : 409;
        String result = "School save : " + status;
        return Response.status(code).entity(result).build();
    }

    public boolean save(School school) {
        logger.info("School save - START");
        schoolDao.openCurrentSessionwithTransaction();
        schoolDao.save(school);
        JSONObject result = new JSONObject();
        try {
            schoolDao.closeCurrentSessionwithTransaction();
        } catch (Exception e) {
            logger.info("School save - BAD:" + school);
            e.printStackTrace();
            return false;
        }
        logger.info("School save - END:" + school);
        return true;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/put")
    public Response updateSchool(School school) {
        boolean status = update(school);
        int code = status ? 200 : 404;
        String result = "School update : " + status;
        return Response.status(code).entity(result).build();
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

    @GET
    @Path("{id: \\d+}")
    @Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
    public School findById(@PathParam("id") int id) {
        School school = null;
        if (id <= 0) {
            logger.info("Attempt to find school with wrong ID:" + id);
        } else {
            logger.info("School findById - START");
            schoolDao.openCurrentSession();
            school = schoolDao.findById(id);
            schoolDao.closeCurrentSession();
            logger.info("School findById - END: id=" + id + " - " + (school != null ? school : null));
        }
        return school;
    }

    @DELETE
    @Path("/delete/{id: \\d+}")
    public Response deleteSchool(@PathParam("id") int id) {
        boolean status = delete(id);
        int code = status ? 200 : 204;
        String result = "School delete : " + status;
        return Response.status(code).entity(result).build();
    }

    public boolean delete(int id) {
        logger.info("School delete - START");
        schoolDao.openCurrentSessionwithTransaction();
        School school = schoolDao.findById(id);
        if (school != null) {
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

    @GET
    @Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
    @Consumes(MediaType.APPLICATION_JSON + "; charset=UTF-8")
    public List<School> findAll() {
        logger.info("School findAll - START");
        schoolDao.openCurrentSession();
        List<School> schools = schoolDao.findAll();
        schoolDao.closeCurrentSession();
        logger.info("School findAll - END: found=" + (schools != null ? schools.size() : null));

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

    @GET
    @Path("/xml/{id: \\d+}")
    @Produces(MediaType.APPLICATION_XML)
    public School getClassroomByXML(@PathParam("id") int id) {
        School school = findById(id);
        return school;
    }

    public void showClassroomOfSchool(int id) {
        School school = findById(id);
        if (school != null) {
            JAXBContext contextObj = null;
            try {
                contextObj = JAXBContext.newInstance(School.class);
            } catch (JAXBException e) {
                e.printStackTrace();
            }

            Marshaller marshallerObj = null;
            try {
                marshallerObj = contextObj.createMarshaller();
            } catch (JAXBException e) {
                e.printStackTrace();
            }
            try {
                marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            } catch (PropertyException e) {
                e.printStackTrace();
            }

            try {
                marshallerObj.marshal(school, new OutputStreamWriter(new FileOutputStream("school_" + school.getSchool_id() + ".xml"), "UTF-8"));
            } catch (JAXBException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    public SchoolDao schoolDao() {
        return schoolDao;
    }
}
