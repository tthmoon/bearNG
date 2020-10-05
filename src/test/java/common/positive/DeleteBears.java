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

@Test
public class DeleteBears extends SetupRA {

    int lastCreatedBear = 0;

    @BeforeMethod
    public void PrepareAndCreatingBear(){
        BearPOJO bearWithPar = new BearPOJO(BearTypes.BROWN, "test", 13.3);

        Response responsePostBear = given().body(bearWithPar)
                .when().post(EndPoints.BEAR);
        responsePostBear.then().assertThat().statusCode(200);

        lastCreatedBear = Integer.parseInt(responsePostBear.body().asString());
    }

    @Test()
    public void deletingSpecificBear(){

        Response responseDeleteBear = given()
                .when().delete(EndPoints.BEAR_SPECIFIC, lastCreatedBear);
        responseDeleteBear.then().assertThat().statusCode(200);
        responseDeleteBear.then().assertThat().contentType(ContentType.JSON);
        Assert.assertEquals(responseDeleteBear.getBody().asString(), "OK");

        Response responseGetBear = given()
                .when().get(EndPoints.BEAR_SPECIFIC, lastCreatedBear);
        responseGetBear.then().assertThat().statusCode(200);
        responseGetBear.then().assertThat().contentType(ContentType.JSON);
        Assert.assertEquals(responseGetBear.getBody().asString(), "EMPTY");
    }

    @Test()
    public void deletingAllBear(){
        Response responseGetBear = given()
                .when().delete(EndPoints.BEAR);
        responseGetBear.then().assertThat().statusCode(200);
        Assert.assertEquals(responseGetBear.getBody().asString(), "OK");

        BearPOJO[] bears = given().when().get(EndPoints.BEAR).as(BearPOJO[].class);
        Assert.assertEquals(bears.length, 0);
    }
}
