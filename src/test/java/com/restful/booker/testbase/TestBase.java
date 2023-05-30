package com.restful.booker.testbase;

import io.restassured.RestAssured;
import org.junit.BeforeClass;

/**
 * Created by Jay Vaghani
 */
public class TestBase {

    @BeforeClass
    public static void inIt() {
        // https://restful-booker.herokuapp.com/booking
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        RestAssured.basePath = "/booking";
    }
}
