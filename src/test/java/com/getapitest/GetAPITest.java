package com.getapitest;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class GetAPITest {

	@Test
	public static void getResponseBody() {
		given().when().get("https://api.spacexdata.com/v4/launches/latest").then().log().all();

	}

	@Test
	public static void verifyResponseStatus() {
		given().when().get("https://api.spacexdata.com/v4/launches/latest").then().assertThat().statusCode(200);
	}

	@Test
	public static void getResponseHeaders() {
		System.out.println("The headers in the response "
				+ get("https://api.spacexdata.com/v4/launches/latest").then().extract().headers());
	}

	@Test
	public static void getSpecificResponseHeader() {
		get("https://api.spacexdata.com/v4/launches/latest").then().header("Content-Encoding", equalTo("gzip"));
	}

	@Test
	public static void getResponseTime() {
		System.out.println("The time taken to fetch the response "
				+ get("https://api.spacexdata.com/v4/launches/latest").timeIn(TimeUnit.MILLISECONDS) + " milliseconds");
	}

	@Test
	public static void verifyResponseContentType() {
		String contentType = get("https://api.spacexdata.com/v4/launches/latest").then().extract().contentType();
		Assert.assertEquals(contentType, "application/json; charset=utf-8");
	}

	@Test
	public static void verifySpecificPartOfResponseBody() {
		get("https://api.spacexdata.com/v4/launches/latest").then().body("fairings.name", equalTo("Starlink-28 (v1.0)"));

	}

}
