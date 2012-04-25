## Java JSON Object Mapper [![Build Status](https://secure.travis-ci.org/scottbyrns/Java-JSON-Object-Mapper.png?branch=master)][travis]

The JSON Object Mapper will hydrate an entity from a JSON string.

### Declaring our Entity.
```java
/**
 * Example entity for testing the JSON object mapper.
 */
public class GeoLocation {
    private double latitude;
    private double longitude;

    public void setLatitude (double latitude) {
        this.latitude = latitude;
    }

    public double getLatitude () {
        return latitude;
    }

    public void setLongitude (double longitude) {
        this.longitude = longitude;
    }

    public double getLongitude () {
        return longitude;
    }
}
```

### Mapping our JSON to our Entity.
```java
GeoLocation location = JSONObjectMapper.mapJSONStringToEntity(
        "{ \"latitude\": 46.0231, \"longitude\": -116.1239 }",
        GeoLocation.class
);
```

_Note: The properties of your entity class must match the JSON keys._

## Exceptions
The JSONObjectMapper can throw one of two exceptions:

### InvalidJSONStringException
```java
catch (InvalidJSONStringException e) {
          // The JSON string was invalid.
}
```

### FatalMappingException
```java
catch (FatalMappingException e) {
          // The entity was not compatible.
}
```

## Support or Contact
Having trouble with the JSON Object Mapper? Generate the documentation with JavaDoc or contact me, (@scottbyrns) directly and I'll help you sort it out.