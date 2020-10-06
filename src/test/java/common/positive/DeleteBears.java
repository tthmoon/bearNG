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
//Тесты на удаление медведя
@Test
public class DeleteBears extends SetupRA {

    int lastCreatedBear = 0;
//    Создание медведя, сохранение его ID  в глобальную переменную
    @BeforeMethod
    public void PrepareAndCreatingBear(){
        BearPOJO bearWithPar = new BearPOJO(BearTypes.BROWN, "test", 13.3);

        Response responsePostBear = given().body(bearWithPar)
                .when().post(EndPoints.BEAR);
        responsePostBear.then().assertThat().statusCode(200);

        lastCreatedBear = Integer.parseInt(responsePostBear.body().asString());
    }
//    Тест на удаление избранного медведя
    @Test
    public void deletingSpecificBear(){
//        Запрос на удаление медведя
        Response responseDeleteBear = given()
                .when().delete(EndPoints.BEAR_SPECIFIC, lastCreatedBear);
        responseDeleteBear.then().assertThat().statusCode(200);
//        Проверка content-type, вызывает ошибку, из-за того, что сервер возвращает непривильный тип
        responseDeleteBear.then().assertThat().contentType(ContentType.JSON);
        Assert.assertEquals(responseDeleteBear.getBody().asString(), "OK");
//        Запроса на получение удаленного медведя
        Response responseGetBear = given()
                .when().get(EndPoints.BEAR_SPECIFIC, lastCreatedBear);
        responseGetBear.then().assertThat().statusCode(200);
//        Проверка content-type, вызывает ошибку, из-за того, что сервер возвращает непривильный тип
        responseGetBear.then().assertThat().contentType(ContentType.JSON);
        Assert.assertEquals(responseGetBear.getBody().asString(), "EMPTY");
    }
//    Тест на удаление всех медведей
    @Test
    public void deletingAllBear(){
        //        Запрос на удаление всех медведей
        Response responseGetBear = given()
                .when().delete(EndPoints.BEAR);
        responseGetBear.then().assertThat().statusCode(200);
        Assert.assertEquals(responseGetBear.getBody().asString(), "OK");
//        Запрос на получение всех медведей
        BearPOJO[] bears = given().when().get(EndPoints.BEAR).as(BearPOJO[].class);
//        Проверка на то, что список медведей пустой
        Assert.assertEquals(bears.length, 0);
    }
}
