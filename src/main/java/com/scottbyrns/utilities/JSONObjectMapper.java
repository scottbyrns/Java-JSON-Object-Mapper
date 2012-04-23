package com.scottbyrns.utilities;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.deser.BeanDeserializerFactory;
import org.codehaus.jackson.map.deser.CustomDeserializerFactory;
import org.codehaus.jackson.map.deser.StdDeserializerProvider;
import org.codehaus.jackson.map.ser.CustomSerializerFactory;

import java.io.IOException;

/**
 * The JSON Object Mapper will take map a JSON String to a simple entity.
 */
public class JSONObjectMapper
{
    private static JSONObjectMapper instance;
    private ObjectMapper defaultObjectMapper = new ObjectMapper();

    private JSONObjectMapper()
    {

        setDefaultObjectMapper(new ObjectMapper());


        //Custom Serializers
        CustomSerializerFactory csf = new CustomSerializerFactory();
        defaultObjectMapper.setSerializerFactory(csf);

        //Custom Deserializers
        BeanDeserializerFactory customDeserializerFactory = new BeanDeserializerFactory();

        StdDeserializerProvider stdDeserializerProvider = new StdDeserializerProvider(customDeserializerFactory);

        defaultObjectMapper.setDeserializerProvider(stdDeserializerProvider);

        defaultObjectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        defaultObjectMapper.configure(SerializationConfig.Feature.AUTO_DETECT_GETTERS, false);
        defaultObjectMapper.configure(SerializationConfig.Feature.AUTO_DETECT_IS_GETTERS, false);
        defaultObjectMapper.setVisibilityChecker(
                defaultObjectMapper.getVisibilityChecker().withFieldVisibility(JsonAutoDetect.Visibility.ANY)
                                           );

        defaultObjectMapper.configure(SerializationConfig.Feature.WRITE_ENUMS_USING_TO_STRING, true);
        defaultObjectMapper.configure(DeserializationConfig.Feature.READ_ENUMS_USING_TO_STRING, true);

    }

    /**
     * Set the default object mapper.
     *
     * @param defaultObjectMapper The default object mapper.
     */
    private void setDefaultObjectMapper(ObjectMapper defaultObjectMapper) {
        this.defaultObjectMapper = defaultObjectMapper;
    }

    /**
     * Get the object mapper instance.
     *
     * @return Object mapper instance.
     */
    public static JSONObjectMapper getInstance()
    {
        if (null == instance) {
            instance = new JSONObjectMapper();
        }

        return instance;
    }

    /**
     * Map a JSON string to an entity.
     *
     * @param JSONString The JSON string to map.
     * @param entity The entity the JSON will be mapped to.
     * @param <T> The type of the entity.
     *
     * @return An instance of the specified entity hydrated with values from the JSON.
     * @throws InvalidJSONStringException The JSON string provided was not valid.
     * @throws FatalMappingException A fatal mapping exception occurred.
     */
    public <T> T mapJSONStringToEntity (String JSONString, Class<T> entity) throws InvalidJSONStringException, FatalMappingException {
        T mappedEntity = null;
        try {
            mappedEntity = (T)defaultObjectMapper.readValue(JSONString, (Class)entity);
        }
        catch (JsonMappingException e) {
            throw new FatalMappingException(e);
        }
        catch (JsonParseException e) {
            throw new InvalidJSONStringException(e);
        }
        catch (IOException e) {
            // I don't think we can fall through to this catch when mapping a string.
            System.out.println("Exception not handled. See JSONObjectMapper#mapJSONStringToEntity.");
        }
        return mappedEntity;
    }
}
