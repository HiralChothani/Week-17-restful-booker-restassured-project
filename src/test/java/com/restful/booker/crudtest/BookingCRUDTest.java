package com.restful.booker.crudtest;

import com.restful.booker.model.AuthPojo;
import com.restful.booker.model.BookingPojo;
import com.restful.booker.testbase.TestBase;
import com.restful.booker.utils.TestUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class BookingCRUDTest extends TestBase {
    static String token;
    private String firstName = "ABC" + TestUtils.getRandomValue();
    private String lastName = "XYZ" + TestUtils.getRandomValue();
    private int totalPrice = TestUtils.getRandomNumber();
    private Boolean depositPaid = true;
    private String newId;
    @Test
    public void test001() {
        //RestAssured.baseURI = "https://restful-booker.herokuapp.com/auth";
        RequestSpecification request = given();
        request.header("Content-Type", "application/json");
        AuthPojo authPojo = new AuthPojo();
        authPojo.setUsername("admin");
        authPojo.setPassword("password123");
        Response response = request.body(authPojo)
                .post("https://restful-booker.herokuapp.com/auth");

        String jsonString = response.asString();
        token = JsonPath.from(jsonString).get("token");
        System.out.println("Token is: " + token);
    }
    @Test
    public void test002() {
        given().log().all()
                .when()
                .get()
                .then()
                .statusCode(200)
                .log().all();
    }
    @Test
    public void test003() {
        HashMap<String, Object> booking = new HashMap<>();
        booking.put("checkin", "2022-01-01");
        booking.put("checkout", "2022-02-01");

        BookingPojo bookingPojo = new BookingPojo();

        bookingPojo.setFirstName(firstName);
        bookingPojo.setLastName(lastName);
        bookingPojo.setTotalPrice(totalPrice);
        bookingPojo.setDepositPaid(depositPaid);
        bookingPojo.setBookingDates(booking);
        bookingPojo.setAdditionalNeed("Access to Gym");

        Response responseBody = given().log().all()
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .header("cookies", "token")
                .body(bookingPojo)
                .when()
                .post().then().extract().response();

        responseBody.then().statusCode(200).log().all();

        System.out.println("response printing ======" + responseBody.asString());
        newId = responseBody.jsonPath().get("bookingid");
        System.out.println("myID ------> " + newId);
    }
    @Test
    public void test004() {
        given().log().all()
                .header("Accept", "application/json")
                .pathParam("bookingId", newId)
                .when()
                .get("/{bookingId}")
                .then()
                .statusCode(200)
                .log().all();
    }
    @Test
    public void test005() {
        HashMap<String, Object> booking = new HashMap<String, Object>();
        booking.put("checkin", "2022-01-01");
        booking.put("checkout", "2022-02-01");

        BookingPojo bookingPojo = new BookingPojo();
        bookingPojo.setFirstName("Name after");
        bookingPojo.setLastName("put request");
        bookingPojo.setTotalPrice(300);
        bookingPojo.setDepositPaid(false);
        bookingPojo.setBookingDates(booking);
        bookingPojo.setAdditionalNeed("Vegetarian Meals");

        RequestSpecification request = RestAssured.given().log().all();
        request.header("Content-Type", "application/json")
                .header("cookie", "token=" + token)
                .log().all();
        //.header("authorization", "bearer" + token)
        Response response = request.body(bookingPojo)
                .put("/" + newId);
        response.then().statusCode(200).log().all();
        System.out.println("put----> " + response.asString());

    }
    @Test
    public void test006() {
        BookingPojo bookingPojo = new BookingPojo();
        bookingPojo.setFirstName("Name After");
        //   bookingPojo.setLastname("Patch");
        RequestSpecification request = RestAssured.given().log().all();
        request.header("Content-Type", "application/json")
                .header("cookie", "token=" + token)
                .log().all();
        Response response = request.body(bookingPojo)
                .patch("/" + newId);
        response.then().statusCode(200).log().all();
        System.out.println("patch----> " + response.asString());
    }
    @Test
    public void test007() {
        given().log().all()
                .contentType(ContentType.JSON)
                .header("cookie", "token=" + token)
                .when()
                .delete("/" + newId)
                .then()
                .statusCode(201)
                .log().all();
    }
}
