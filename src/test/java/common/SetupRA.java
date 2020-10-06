package common;

import io.restassured.RestAssured;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import settings.Server;

//Класс для подготовки спецификации для Rest assured.
//Используется для конфигурирования фреймворка, как следствие его нужно наследовать в каждом тесте, где используется данный фреймворк.
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
