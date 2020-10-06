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

//Тесты на чтение медведй
public class ReadBears extends SetupRA {
    int lastCreatedBear = 0;
    BearPOJO bear = new BearPOJO(BearTypes.BROWN, "test", 13.3);
    //  Удаление всех медведей, создание медведя, сохранение его ID и тела  в глобальную переменную
    @BeforeMethod
    public void PrepareAndCreatingBear(){
        Response responseDeleteBear = given()
                .when().delete(EndPoints.BEAR);
        responseDeleteBear.then().assertThat().statusCode(200);
        Assert.assertEquals(responseDeleteBear.getBody().asString(), "OK");

        Response responsePostBear = given().body(bear)
                .when().post(EndPoints.BEAR);
        responsePostBear.then().assertThat().statusCode(200);

        lastCreatedBear = Integer.parseInt(responsePostBear.body().asString());
    }
//    Тест на чтение определенного медведя
    @Test
    public void readSpecificBear(){
//        Запрос на чтение определенного медведя
        Response responseGetBear = given()
                .when().get(EndPoints.BEAR_SPECIFIC, lastCreatedBear);
        responseGetBear.then().assertThat().statusCode(200);
//        Проверка content-type, вызывает ошибку, из-за того, что сервер возвращает непривильный тип
        responseGetBear.then().assertThat().contentType(ContentType.JSON);
        responseGetBear.then().assertThat()
                .body("bear_id", equalTo(lastCreatedBear))
                .body("bear_type", equalTo(bear.getBear_type().toUpperCase()))
                .body("bear_name", equalTo(bear.getBear_name().toUpperCase()))
                .body("bear_age", equalTo(bear.getBear_age()));
    }
//    Тест на чтение всех медведей
    @Test
    public void ReadAllBear(){
//        Запрос на чтение всех медведей
        Response responseGetBear = given()
                .when().get(EndPoints.BEAR);
        responseGetBear.then().assertThat().statusCode(200);
        BearPOJO[] bears = responseGetBear.body().as(BearPOJO[].class);
//        Проверка на то, что список медведей из одного
        Assert.assertEquals(bears.length, 1);
    }
}
