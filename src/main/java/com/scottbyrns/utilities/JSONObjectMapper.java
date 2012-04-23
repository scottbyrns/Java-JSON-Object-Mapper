package com.scottbyrns.utilities;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.deser.CustomDeserializerFactory;
import org.codehaus.jackson.map.deser.StdDeserializerProvider;
import org.codehaus.jackson.map.ser.CustomSerializerFactory;

/**
 * Factory for configuring Jackson to use all our custom settings, serializers, and deserializers.
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
        getDefaultObjectMapper().setSerializerFactory(csf);

        //Custom Deserializers
        CustomDeserializerFactory customDeserializerFactory = new CustomDeserializerFactory();

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

    private void setDefaultObjectMapper(ObjectMapper defaultObjectMapper) {
        this.defaultObjectMapper = defaultObjectMapper;
    }

    public static JSONObjectMapper getInstance()
    {
        if (null == instance) {
            instance = new JSONObjectMapper();
        }
        return instance;
    }

    public ObjectMapper getDefaultObjectMapper()
    {
        return defaultObjectMapper;
    }
}
