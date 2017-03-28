package schoolManager.service;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.deser.std.StdDeserializer;
import schoolManager.entity.School;

import java.io.IOException;

/**
 * Created by forpost on 28.03.17.
 */
public class CustomSchoolDeserializer extends StdDeserializer<School> {

    public CustomSchoolDeserializer() {
        this(null);
    }

    public CustomSchoolDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public School deserialize(
            JsonParser jsonparser,
            DeserializationContext context)
            throws IOException, JsonProcessingException {
        School school = new School();
        school.setSchool_id(jsonparser.getIntValue());
        return school;
    }
}