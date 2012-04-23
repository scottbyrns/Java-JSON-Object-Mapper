Java JSON Object Mapper
====================
Here is a generic library to map JSON to an entity.

Usage
-----
Simply create a POJO who's properties names match the property keys in your JSON.

```json

{
    latitude: 46.0231,
    longitude: -116.1239
}

```

```java

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

Now that we have an entity that we can map our JSON to we simply call up the JSONObjectMapper and

```java

GeoLocation geolocation = (GeoLocation)JSONObjectMapper.getInstance().getDefaultObjectMapper().readValue(
        "{latitude: 46.0231, longitude: -116.1239}",
        GeoLocation.class
);

```