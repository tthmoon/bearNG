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

//Тест на создание медведя по всем типам
@Test
public class CreateBearByType extends SetupRA {

    @Test( dataProviderClass = DataProviders.class,
            dataProvider = "bearTypes")
    public void creatingBearsByTypeUpperCase(String btype) {
//        Создание POJO медведя, с "корректным" телом
        BearPOJO bear = new BearPOJO(btype, "test", 13.3);
//        Выполнение запроса за создание медведя
        Response responsePostBear = given().body(bear)
                .when().post(EndPoints.BEAR);
        responsePostBear.then().assertThat().statusCode(200);
//        Получение кода созданного медведя
        int bearID = Integer.parseInt(responsePostBear.body().asString());
//        Запрос на получение медведя по ID
        Response responseGetBear = given()
                .when().get(EndPoints.BEAR_SPECIFIC, bearID);
        responseGetBear.then().assertThat().statusCode(200);
//        Проверка content-type, вызывает ошибку, из-за того, что сервер возвращает непривильный тип
        responseGetBear.then().assertThat().contentType(ContentType.JSON);
//        Провека тела медведя. Провека возможна  через десерилиазации ответа из json в POJO.
//        Но данный вариант считаю лучше читаемым
        responseGetBear.then().assertThat()
                .body("bear_id", equalTo(bearID))
                .body("bear_type", equalTo(bear.getBear_type().toUpperCase()))
                .body("bear_name", equalTo(bear.getBear_name().toUpperCase()))
                .body("bear_age", equalTo(bear.getBear_age()));
    }
}
