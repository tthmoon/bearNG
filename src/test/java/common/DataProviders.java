package common;

import org.testng.annotations.DataProvider;

public class DataProviders {
    @DataProvider(name = "bearTypes")
    public Object[] bearTypes() {
        return new String[]{
                "BROWN",
                "POLAR",
                "BLACK",
                "GUMMY"
        };
    }
}
