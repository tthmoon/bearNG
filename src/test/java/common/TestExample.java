package common;

import bear.BearPOJO;
import bear.BearTypes;
import endpoint.EndPoints;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TestExample extends SetupRA {
    @Test
    public void test(){
        BearPOJO bear = new BearPOJO(BearTypes.BROWN, "test", 12.3);
        System.out.println(given()
                .body(bear)
                .when()
                .post(EndPoints.BEAR)
                .body().asString()
    };
}
