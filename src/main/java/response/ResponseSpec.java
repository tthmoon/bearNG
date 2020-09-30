package response;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.containsString;

public class ResponseSpec {
    static ResponseSpecification positiveRespose(){
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectBody(containsString("success"))
                .build();
    }
}
