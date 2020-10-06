package common;

import bear.BearTypes;
import org.testng.annotations.DataProvider;

//Класс для описания кастомных датапровайдеров.
public class DataProviders {
//    Датапровайдер для хранения списка типов медведей
    @DataProvider(name = "bearTypes")
    public Object[] bearTypes() {
        return new String[]{
                BearTypes.BROWN,
                BearTypes.POLAR,
                BearTypes.BLACK,
                BearTypes.GUMMY
        };
    }
}
