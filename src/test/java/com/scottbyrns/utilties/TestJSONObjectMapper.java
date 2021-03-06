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
package com.scottbyrns.utilties;

import com.scottbyrns.utilities.FatalMappingException;
import com.scottbyrns.utilities.InvalidJSONStringException;
import com.scottbyrns.utilities.JSONObjectMapper;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.Assert.*;


/**
 * Testing the object mapper.
 */
public class TestJSONObjectMapper
{

    private String JSONString        = "{\"latitude\":46.0231,\"longitude\":-116.1239}";
    private String InvalidJSONString = "{ \"latitude\" 46.0231, \"longitude\": -116.1239 }";
    private String MembersJSON = "{\"status\":200,\"response\":{\"id\":12,\"tribeList\":null,\"name\":null,\"persona\":null}}";
    private String JSONMap = "{\"stringGeoLocationMap\":{{ \"latitude\": 46.0231, \"longitude\": -116.1239 }:\"keyString\"}}";

    /**
     * Testing mapping a JSON String to an Entity.
     */
    @Test
    public void testMappingAJSONStringToAnEntity()
    {
        try
        {
            GeoLocation location = JSONObjectMapper.mapJSONStringToEntity(JSONString,
                                                                          GeoLocation.class);
            assertEquals("The latitude of our hydrate d location object is not the same as it was in the provided JSON.",
                         location.getLatitude(),
                         46.0231);
            assertEquals("The longitude of our hydrated location object is not the same as it was in the provided JSON.",
                         location.getLongitude(),
                         -116.1239);
        }
        catch (InvalidJSONStringException e)
        {
            fail("The object mapper incorrectly declared our JSON string to be invalid.");
        }
        catch (FatalMappingException e)
        {
            fail("The object mapper incorrectly declared our entity incompatible");
        }
    }

//    @Test
    public void testDeserializerModulePlugin () {
        try {

            JsonDeserializer<GeoLocation> geoLocationJsonDeserializer = new JsonDeserializer<GeoLocation>() {
                @Override
                public GeoLocation deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException
                {

                    return null;  //To change body of implemented methods use File | Settings | File Templates.
                }
            };
            JSONObjectMapper.registerDeserializerModule("GeoLocation", geoLocationJsonDeserializer, GeoLocation.class);

            JSONMap jsonMap = JSONObjectMapper.mapJSONStringToEntity(JSONMap, JSONMap.class);
            GeoLocation location = jsonMap.getStringGeoLocationMap().keySet().iterator().next();
            assertEquals("The latitude of our hydrate d location object is not the same as it was in the provided JSON.",
                         location.getLatitude(),
                         46.0231);
            assertEquals("The longitude of our hydrated location object is not the same as it was in the provided JSON.",
                         location.getLongitude(),
                         -116.1239);
        }
        catch (InvalidJSONStringException e)
        {
            e.printStackTrace();
            fail("The object mapper incorrectly declared our JSON string to be invalid.");
        }
        catch (FatalMappingException e)
        {
            e.printStackTrace();
            fail("The object mapper incorrectly declared our entity incompatible");
        }
    }

    /**
     * Testing mapping a JSON String to an Entity.
     */
    @Test
    public void testMappingAJSONNodeStringToAnEntity()
    {
        try
        {
            GeoLocation location = JSONObjectMapper.mapJSONNodeStringToEntity("{\"status\":200, \"response\":" + JSONString + "}", "response",
                                                                          GeoLocation.class);
            assertEquals("The latitude of our hydrated location object is not the same as it was in the provided JSON.",
                         location.getLatitude(),
                         46.0231);
            assertEquals("The longitude of our hydrated location object is not the same as it was in the provided JSON.",
                         location.getLongitude(),
                         -116.1239);
        }
        catch (InvalidJSONStringException e)
        {
            fail("The object mapper incorrectly declared our JSON string to be invalid.");
        }
        catch (FatalMappingException e)
        {
            fail("The object mapper incorrectly declared our entity incompatible");
        }
    }

//    /**
//     * Test invalid JSON to ensure that it produces an exception.
//     */
//    @Test
//    public void testMembersJson()
//    {
//        boolean jsonIsInvalid = false;
//        try
//        {
//            GeoLocation location = JSONObjectMapper.mapJSONStringToEntity(MembersJSON,
//                                                                          GeoLocation.class);
//        }
//        catch (InvalidJSONStringException e)
//        {
//            jsonIsInvalid = true;
//        }
//        catch (FatalMappingException e)
//        {
//            fail("The object mapper incorrectly declared our entity incompatible");
//        }
//
//        assertTrue("Our invalid JSON failed to raise an exception.",
//                   jsonIsInvalid);
//    }

    /**
     * Test invalid JSON to ensure that it produces an exception.
     */
    @Test
    public void testInvalidJSON()
    {
        boolean jsonIsInvalid = false;
        try
        {
            GeoLocation location = JSONObjectMapper.mapJSONStringToEntity(InvalidJSONString,
                                                                          GeoLocation.class);
        }
        catch (InvalidJSONStringException e)
        {
            jsonIsInvalid = true;
        }
        catch (FatalMappingException e)
        {
            fail("The object mapper incorrectly declared our entity incompatible");
        }

        assertTrue("Our invalid JSON failed to raise an exception.",
                   jsonIsInvalid);
    }

    /**
     * Testing converting an entity to a JSON String.
     */
    @Test
    public void testConvertingAnEntityToAJSONString()
    {
        GeoLocation location = null;
        try
        {
            location = JSONObjectMapper.mapJSONStringToEntity(JSONString,
                                                              GeoLocation.class);
        }
        catch (InvalidJSONStringException e)
        {
            fail("Invalid JSON Provided.");
        }
        catch (FatalMappingException e)
        {
            fail("The object mapper was unable to convert your entity to JSON.");
        }


        try
        {
            String jsonString = JSONObjectMapper.convertEntityToJSON(location);

            assertEquals("The JSON produced should be the same as the JSON it was mapped from.",
                         JSONString,
                         jsonString);
        }
        catch (FatalMappingException e)
        {
            fail("A fatal mapping exception was thrown.");
        }
    }

}
