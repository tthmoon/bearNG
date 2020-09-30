package common;

import io.restassured.RestAssured;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import settings.Server;

public class SetupRA {
    @BeforeMethod
    public void configureRestAssured() {
        RequestSpecification requestSpec = new RequestSpecBuilder()
                .setBaseUri(Server.IP)
                .setPort(Server.PORT)
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .build();
        RestAssured.requestSpecification = requestSpec;
    }
}
