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

/**
 * Example entity for testing the JSON object mapper.
 */
public class GeoLocation
{
    private double latitude;
    private double longitude;

    public void setLatitude(double latitude)
    {
        this.latitude = latitude;
    }

    public double getLatitude()
    {
        return latitude;
    }

    public void setLongitude(double longitude)
    {
        this.longitude = longitude;
    }

    public double getLongitude()
    {
        return longitude;
    }
}