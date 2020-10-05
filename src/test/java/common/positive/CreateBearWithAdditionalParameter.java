package common.positive;
import bear.BearPOJO;
import bear.BearTypes;
import common.SetupRA;
import endpoint.EndPoints;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.not;

@Test
public class CreateBearWithAdditionalParameter extends SetupRA {

    @Test(priority = 1)
    public void creatingBearWithAdditionalParameter(){
        String additionalVar = "testpar";
        String additionalVal = "testval";
        BearPOJO bearWithPar = new BearPOJO(BearTypes.BROWN, "test", 13.3);
        bearWithPar.setMap(additionalVar, additionalVal);

        Response responsePostBear = given().body(bearWithPar)
                .when().post(EndPoints.BEAR);
        responsePostBear.then().assertThat().statusCode(200);

        int bearID = Integer.parseInt(responsePostBear.body().asString());

        Response responseGetBear = given()
                .when().get(EndPoints.BEAR_SPECIFIC, bearID);
        responseGetBear.then().assertThat().statusCode(200);
        responseGetBear.then().assertThat().contentType(ContentType.JSON);
        responseGetBear.then().assertThat()
                .body("bear_id", equalTo(bearID))
                .body("bear_type", equalTo(bearWithPar.getBear_type().toUpperCase()))
                .body("bear_name", equalTo(bearWithPar.getBear_name().toUpperCase()))
                .body("bear_age", equalTo(bearWithPar.getBear_age()))
                .body("$", not(hasKey(additionalVar))
                );
    }
}
