package com.scottbyrns.utilties;

import com.scottbyrns.utilities.FatalMappingException;
import com.scottbyrns.utilities.InvalidJSONStringException;
import com.scottbyrns.utilities.JSONObjectMapper;
import org.junit.Test;

import static junit.framework.Assert.*;


/**
 * Testing the object mapper.
 */
public class TestJSONObjectMapper {

    private String JSONString = "{ \"latitude\": 46.0231, \"longitude\": -116.1239 }";
    private String InvalidJSONString = "{ \"latitude\" 46.0231, \"longitude\": -116.1239 }";

    /**
     * Testing mapping a JSON String to an Entity.
     */
    @Test
    public void testMappingAJSONStringToAnEntity() {
        try {
            GeoLocation location = JSONObjectMapper.mapJSONStringToEntity(
                    JSONString,
                    GeoLocation.class
            );
            assertEquals(
                    "The latitude of our hydrated location object is not the same as it was in the provided JSON.",
                    location.getLatitude(),
                    46.0231
            );
            assertEquals(
                    "The longitude of our hydrated location object is not the same as it was in the provided JSON.",
                    location.getLongitude(),
                    -116.1239
            );
        }
        catch (InvalidJSONStringException e) {
            fail("The object mapper incorrectly declared our JSON string to be invalid.");
        }
        catch (FatalMappingException e) {
            fail("The object mapper incorrectly declared our entity incompatible");
        }
    }

    /**
     * Test invalid JSON to ensure that it produces an exception.
     */
    @Test
    public void testInvalidJSON () {
        boolean jsonIsInvalid = false;
        try {
            GeoLocation location = JSONObjectMapper.mapJSONStringToEntity(
                    InvalidJSONString,
                    GeoLocation.class
            );
        }
        catch (InvalidJSONStringException e) {
            jsonIsInvalid = true;
        }
        catch (FatalMappingException e) {
            fail("The object mapper incorrectly declared our entity incompatible");
        }

        assertTrue(
                "Our invalid JSON failed to raise an exception.",
                jsonIsInvalid
        );
    }

}
