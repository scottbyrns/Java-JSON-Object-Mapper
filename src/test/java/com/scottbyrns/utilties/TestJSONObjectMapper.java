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
import org.junit.Test;

import static junit.framework.Assert.*;


/**
 * Testing the object mapper.
 */
public class TestJSONObjectMapper {

    private String JSONString = "{\"latitude\":46.0231,\"longitude\":-116.1239}";
    private String InvalidJSONString = "{ \"latitude\" 46.0231, \"longitude\": -116.1239 }";
    private String dayLumRentalsJSON = "{\"status\":\"ok\",\"posts\":[{\"id\":3772,\"slug\":\"classic-post-n-pier\",\"title\":\"**** CLASSIC POST-N-PIER ****\",\"content\":\"<p><strong>Full Description</strong>:  A great classic post-n-pier kamaaina house with wood floors, built in cabinets and much more.</p>\\n<p><strong>Address</strong>: 615 Kekuanaoa<br />\\n<strong>City</strong>: Hilo<br />\\n<strong>State</strong>: HI<br />\\n<strong>Zip</strong>: 96720<br />\\n<strong>Rent</strong>: $850.00<br />\\n<strong>Beds</strong>: 2<br />\\n<strong>Baths</strong>: 1<br />\\n<strong>Square Feet:</strong> 840 sq. ft.<br />\\n<strong>Status</strong>: Available 05/11/12<br />\\n<strong>Pet:</strong> No<br />\\n<strong>SEC 8?: </strong>No<br />\\n<strong>Utilities Inclu: </strong>No  <strong><br />\\nDeposit: </strong>$850.00 <strong> </strong></p>\\n<p><strong>Home Features:</strong></p>\\n<ul>\\n<li>Range</li>\\n<li>Refrigerator</li>\\n</ul>\\n<p><strong>DIRECTIONS:</strong> <!-- @font-face {   font-family: \\\"Times New Roman\\\"; }@font-face {   font-family: \\\"Arial\\\"; }@font-face {   font-family: \\\"Courier New\\\"; }@font-face {   font-family: \\\"Wingdings\\\"; }@font-face {   font-family: \\\"Tahoma\\\"; }p.MsoNormal, li.MsoNormal, div.MsoNormal { margin: 0in 0in 0.0001pt; text-align: center; font-size: 12pt; font-family: \\\"Times New Roman\\\"; }p.MsoHeader, li.MsoHeader, div.MsoHeader { margin: 0in 0in 0.0001pt; text-align: center; font-size: 12pt; font-family: \\\"Times New Roman\\\"; }p.MsoFooter, li.MsoFooter, div.MsoFooter { margin: 0in 0in 0.0001pt; text-align: center; font-size: 12pt; font-family: \\\"Times New Roman\\\"; }p.MsoTitle, li.MsoTitle, div.MsoTitle { margin: 0in 0in 0.0001pt; text-align: center; font-size: 10pt; font-family: Arial; text-decoration: underline; font-weight: bold; }a:link, span.MsoHyperlink { color: blue; text-decoration: underline; }a:visited, span.MsoHyperlinkFollowed { color: purple; text-decoration: underline; }span.CharChar3 { font-size: 10pt; text-decoration: underline; font-weight: bold; }p.BalloonText, li.BalloonText, div.BalloonText { margin: 0in 0in 0.0001pt; text-align: center; font-size: 8pt; font-family: Tahoma; }span.CharChar2 { font-size: 8pt; }span.CharChar1 { font-size: 12pt; }span.CharChar { font-size: 12pt; }p.ListParagraph, li.ListParagraph, div.ListParagraph { margin: 0in 0in 0.0001pt 0.5in; text-align: center; font-size: 12pt; font-family: \\\"Times New Roman\\\"; }div.Section1 { page: Section1; }ol { margin-bottom: 0in; }ul { margin-bottom: 0in; } -->From Manono Street, turn Left onto Kekuanaoa heading towards airport, house in middle of first block, on the left, tan with brown siding.</p>\\n\\r\\n<div class=\\\"ngg-galleryoverview\\\" id=\\\"ngg-gallery-217-3772\\\">\\r\\n\\r\\n\\r\\n\\t\\r\\n\\t<!-- Thumbnails -->\\r\\n\\t\\t\\r\\n\\t<div id=\\\"ngg-image-976\\\" class=\\\"ngg-gallery-thumbnail-box\\\"  >\\r\\n\\t\\t<div class=\\\"ngg-gallery-thumbnail\\\" >\\r\\n\\t\\t\\t<a href=\\\"http://day-lumrentals.com/wp-content/gallery/392/392a.jpg\\\" title=\\\" \\\" class=\\\"shutterset_set_217\\\" >\\r\\n\\t\\t\\t\\t\\t\\t\\t\\t<img title=\\\"392a\\\" alt=\\\"392a\\\" src=\\\"http://day-lumrentals.com/wp-content/gallery/392/thumbs/thumbs_392a.jpg\\\" width=\\\"100\\\" height=\\\"75\\\" />\\r\\n\\t\\t\\t\\t\\t\\t\\t</a>\\r\\n\\t\\t</div>\\r\\n\\t</div>\\r\\n\\t\\r\\n\\t\\t\\r\\n \\t\\t\\r\\n\\t<div id=\\\"ngg-image-977\\\" class=\\\"ngg-gallery-thumbnail-box\\\"  >\\r\\n\\t\\t<div class=\\\"ngg-gallery-thumbnail\\\" >\\r\\n\\t\\t\\t<a href=\\\"http://day-lumrentals.com/wp-content/gallery/392/392b.jpg\\\" title=\\\" \\\" class=\\\"shutterset_set_217\\\" >\\r\\n\\t\\t\\t\\t\\t\\t\\t\\t<img title=\\\"392b\\\" alt=\\\"392b\\\" src=\\\"http://day-lumrentals.com/wp-content/gallery/392/thumbs/thumbs_392b.jpg\\\" width=\\\"100\\\" height=\\\"75\\\" />\\r\\n\\t\\t\\t\\t\\t\\t\\t</a>\\r\\n\\t\\t</div>\\r\\n\\t</div>\\r\\n\\t\\r\\n\\t\\t\\r\\n \\t\\t\\r\\n\\t<div id=\\\"ngg-image-978\\\" class=\\\"ngg-gallery-thumbnail-box\\\"  >\\r\\n\\t\\t<div class=\\\"ngg-gallery-thumbnail\\\" >\\r\\n\\t\\t\\t<a href=\\\"http://day-lumrentals.com/wp-content/gallery/392/392c.jpg\\\" title=\\\" \\\" class=\\\"shutterset_set_217\\\" >\\r\\n\\t\\t\\t\\t\\t\\t\\t\\t<img title=\\\"392c\\\" alt=\\\"392c\\\" src=\\\"http://day-lumrentals.com/wp-content/gallery/392/thumbs/thumbs_392c.jpg\\\" width=\\\"100\\\" height=\\\"75\\\" />\\r\\n\\t\\t\\t\\t\\t\\t\\t</a>\\r\\n\\t\\t</div>\\r\\n\\t</div>\\r\\n\\t\\r\\n\\t\\t\\r\\n \\t \\t\\r\\n\\t<!-- Pagination -->\\r\\n \\t<div class='ngg-clear'></div>\\n \\t\\r\\n</div>\\r\\n\\r\\n\\n\",\"custom_fields\":{\"_wp_cf_addnl_map_value\":[\"615 Kekuanaoa, HIlo, HI 96720\"],\"_wp_cf_price_value\":[\"850\"],\"_wp_cf_price_suffix_value\":[\"/Month \"],\"_wp_cf_bedrooms_value\":[\"2\"],\"_wp_cf_bathrooms_value\":[\"1\"],\"_wp_cf_addnl_status_value\":[\"Available 05/11/12\"]}}],\"count\":1,\"pages\":61,\"count_total\":61}";

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

    @Test
    public void testConvertingAnEntityToAJSONString () {
        GeoLocation location = null;
        try {
            location = JSONObjectMapper.mapJSONStringToEntity(
                    JSONString,
                    GeoLocation.class
            );
        }
        catch (InvalidJSONStringException e) {
            fail("Invalid JSON Provided.");
        }
        catch (FatalMappingException e) {
            fail("The object mapper was unable to convert your entity to JSON.");
        }


        try {
            String jsonString = JSONObjectMapper.convertEntityToJSON(location);

            assertEquals(
                    "The JSON produced should be the same as the JSON it was mapped from.",
                    JSONString,
                    jsonString
            );
        }
        catch (FatalMappingException e) {
            fail("A fatal mapping exception was thrown.");
        }
    }

}
