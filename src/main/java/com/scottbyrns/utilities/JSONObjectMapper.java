/**
 *
 * Copyright (C) 2011, 2012 by Scott Byrns
 * http://github.com/scottbyrns
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 */
package com.scottbyrns.utilities;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.Version;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.map.*;
import org.codehaus.jackson.map.deser.BeanDeserializerFactory;
import org.codehaus.jackson.map.deser.BeanDeserializerModifier;
import org.codehaus.jackson.map.deser.StdDeserializerProvider;
import org.codehaus.jackson.map.deser.ValueInstantiators;
import org.codehaus.jackson.map.module.SimpleModule;
import org.codehaus.jackson.map.ser.CustomSerializerFactory;
import org.codehaus.jackson.type.TypeReference;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * The JSON Object Mapper will take map a JSON String to a simple entity.
 */
public class JSONObjectMapper
{

    public static Logger logger = Logger.getLogger(JSONObjectMapper.class);


    private static JSONObjectMapper instance;
    private ObjectMapper defaultObjectMapper = new ObjectMapper();

    private JSONObjectMapper()
    {
        setDefaultObjectMapper(new ObjectMapper());


        //Custom Serializers
        CustomSerializerFactory csf = new CustomSerializerFactory();
        defaultObjectMapper.setSerializerFactory(csf);

        defaultObjectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        defaultObjectMapper.configure(SerializationConfig.Feature.AUTO_DETECT_GETTERS, false);
        defaultObjectMapper.configure(SerializationConfig.Feature.AUTO_DETECT_IS_GETTERS, false);
        defaultObjectMapper.setVisibilityChecker(defaultObjectMapper.getVisibilityChecker().withFieldVisibility(JsonAutoDetect.Visibility.ANY));

        defaultObjectMapper.configure(SerializationConfig.Feature.WRITE_ENUMS_USING_TO_STRING, true);
        defaultObjectMapper.configure(DeserializationConfig.Feature.READ_ENUMS_USING_TO_STRING,
                                      true);

        logger.info("\n\n\n JSON Object Mapper\n https://github.com/scottbyrns/Java-JSON-Object-Mapper\n\n Licensed under the Apache License, Version 2.0\n\n ------( Please Contribute Failing Unit Tests )------\n\n If you find particular inputs that cause issue\n please write a failing unit test and send a pull\n request on github.\n\n\n");
    }

    /**
     * Set the default object mapper.
     *
     * @param defaultObjectMapper The default object mapper.
     */
    private void setDefaultObjectMapper(ObjectMapper defaultObjectMapper)
    {
        this.defaultObjectMapper = defaultObjectMapper;
    }

    /**
     * Get the object mapper instance.
     *
     * @return Object mapper instance.
     */
    private static JSONObjectMapper getInstance()
    {
        if (null == instance)
        {
            logger.debug("Creating the JSONObjectMapper singleton instance.");
            instance = new JSONObjectMapper();
        }

        return instance;
    }

    /**
     * Map a JSON string to an entity.
     *
     * @param JSONString The JSON string to map.
     * @param entity     The entity the JSON will be mapped to.
     * @param <T>        The type of the entity.
     * @return An instance of the specified entity hydrated with values from the JSON.
     * @throws InvalidJSONStringException The JSON string provided was not valid.
     * @throws FatalMappingException      A fatal mapping exception occurred.
     */
    public static <T> T mapJSONStringToEntity(String JSONString, Class<T> entity) throws InvalidJSONStringException, FatalMappingException
    {
        logger.debug("JSON Input:\n      " + JSONString);
        T mappedEntity = null;
        try
        {
            mappedEntity = JSONObjectMapper.getInstance().defaultObjectMapper.<T>readValue(JSONString,
                                                                                           entity);
        }
        catch (JsonMappingException e)
        {
            logger.debug("\n\n\nJackson threw a JsonMapping Exception.\n\n\n");
            throw new FatalMappingException(e);
        }
        catch (JsonParseException e)
        {
            logger.debug("\n\n\nJackson threw a JsonParse Exception. This is probably due to malformed JSON.\n\n\n");
            throw new InvalidJSONStringException(e);
        }
        catch (IOException e)
        {
            // I don't think we can fall through to this catch when mapping a string.
            logger.debug("\n\n\nException not handled. See JSONObjectMapper#mapJSONStringToEntity.\n\n\n");
        }
        return mappedEntity;
    }

    public static <T> T mapJSONNodeStringToEntity(String JSONString, String rootNode, Class<T> entity) throws InvalidJSONStringException, FatalMappingException
    {
        logger.debug("JSON Input:\n      " + JSONString);
        T mappedEntity = null;
        try
        {
            try
            {
                JSONObject object = new JSONObject(JSONString);
                String node = object.getString(rootNode);

                mappedEntity = JSONObjectMapper.getInstance().defaultObjectMapper.<T>readValue(node,
                                                                                               entity);
            }
            catch (JSONException e)
            {
                e.printStackTrace();
                // NOP
            }
            //            mappedEntity = JSONObjectMapper.getInstance().defaultObjectMapper.<T>readValue(JSONString,
            //                                                                                           entity);
        }
        catch (JsonMappingException e)
        {
            logger.debug("\n\n\nJackson threw a JsonMapping Exception.\n\n\n");
            throw new FatalMappingException(e);
        }
        catch (JsonParseException e)
        {
            logger.debug("\n\n\nJackson threw a JsonParse Exception. This is probably due to malformed JSON.\n\n\n");
            throw new InvalidJSONStringException(e);
        }
        catch (IOException e)
        {
            // I don't think we can fall through to this catch when mapping a string.
            logger.debug("\n\n\nException not handled. See JSONObjectMapper#mapJSONStringToEntity.\n\n\n");
        }
        return mappedEntity;
    }

    /**
     * Convert an entity instance to a JSON string.
     *
     * @param entity The entity to convert to JSON
     * @return JSON String representation of the entity.
     * @throws FatalMappingException There was a fatal mapping exception.
     */
    public static String convertEntityToJSON(Object entity) throws FatalMappingException
    {
        String outputString;
        try
        {
            outputString = JSONObjectMapper.getInstance().defaultObjectMapper.writeValueAsString(entity);
        }
        catch (JsonMappingException e)
        {
            logger.debug("\n\n\nJackson threw a JsonMapping Exception.\n\n\n");
            throw new FatalMappingException(e);
        }
        catch (JsonGenerationException e)
        {
            logger.debug("\n\n\nJackson threw a JsonGeneration Exception. Ensure your entities break down into simple types.\n\n\n");
            throw new FatalMappingException(e);
        }
        catch (IOException e)
        {
            logger.debug("\n\n\nJackson threw an IO Exception.\n\n\n");
            throw new FatalMappingException(e);
        }
        logger.debug("JSON Output:\n     " + outputString);
        return outputString;
    }

    public static <T> void registerDeserializerModule(String deserializerName, JsonDeserializer jsonDeserializer, Class<T> entityClass)
    {
        SimpleModule module = new SimpleModule(
                deserializerName,
                new Version(1, 0, 0, null)
        );
        module.addDeserializer(entityClass, jsonDeserializer);
        getInstance().defaultObjectMapper.registerModule(module);
    }
}
