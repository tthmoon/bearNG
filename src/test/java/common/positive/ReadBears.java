package common.positive;

import bear.BearPOJO;
import bear.BearTypes;
import common.SetupRA;
import endpoint.EndPoints;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ReadBears extends SetupRA {
    int lastCreatedBear = 0;
    BearPOJO bear = new BearPOJO(BearTypes.BROWN, "test", 13.3);

    @BeforeMethod
    public void PrepareAndCreatingBear(){

        Response responseGetBear = given()
                .when().delete(EndPoints.BEAR);
        responseGetBear.then().assertThat().statusCode(200);
        Assert.assertEquals(responseGetBear.getBody().asString(), "OK");

        Response responsePostBear = given().body(bear)
                .when().post(EndPoints.BEAR);
        responsePostBear.then().assertThat().statusCode(200);

        lastCreatedBear = Integer.parseInt(responsePostBear.body().asString());
    }

    @Test
    public void readSpecificBear(){
        Response responseGetBear = given()
                .when().get(EndPoints.BEAR_SPECIFIC, lastCreatedBear);
        responseGetBear.then().assertThat().statusCode(200);
        responseGetBear.then().assertThat().contentType(ContentType.JSON);
        responseGetBear.then().assertThat()
                .body("bear_id", equalTo(lastCreatedBear))
                .body("bear_type", equalTo(bear.getBear_type().toUpperCase()))
                .body("bear_name", equalTo(bear.getBear_name().toUpperCase()))
                .body("bear_age", equalTo(bear.getBear_age()));
    }

    @Test
    public void ReadAllBear(){
        Response responseGetBear = given()
                .when().get(EndPoints.BEAR);
        responseGetBear.then().assertThat().statusCode(200);

        BearPOJO[] bears = given().when().get(EndPoints.BEAR).as(BearPOJO[].class);
        Assert.assertEquals(bears.length, 1);
    }
}
