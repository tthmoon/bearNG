package common.positive;


import bear.BearPOJO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.DataProviders;
import common.SetupRA;
import endpoint.EndPoints;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Test
public class CreateBearByType extends SetupRA {

    @Test(priority = 1, dataProviderClass = DataProviders.class,
            dataProvider = "bearTypes")
    public void creatingBearsByTypeUpperCase(String btype) {
        BearPOJO bear = new BearPOJO(btype, "test", 13.3);

        Response responsePostBear = given().body(bear)
                .when().post(EndPoints.BEAR);
        responsePostBear.then().assertThat().statusCode(200);

        int bearID = Integer.parseInt(responsePostBear.body().asString());

        Response responseGetBear = given()
                .when().get(EndPoints.BEAR_SPECIFIC, bearID);
        responseGetBear.then().assertThat().statusCode(200);
        responseGetBear.then().assertThat().contentType(ContentType.JSON);
        responseGetBear.then().assertThat()
                .body("bear_id", equalTo(bearID))
                .body("bear_type", equalTo(bear.getBear_type().toUpperCase()))
                .body("bear_name", equalTo(bear.getBear_name().toUpperCase()))
                .body("bear_age", equalTo(bear.getBear_age()));
    }
}
