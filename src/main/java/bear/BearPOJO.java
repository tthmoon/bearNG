package bear;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import groovyjarjarantlr4.v4.runtime.misc.MultiMap;

import java.util.HashMap;
import java.util.Map;

//POJO класс для создания тела медведя. Принимает дополнительные поля для тестирования
public class BearPOJO {

    private String bear_name;
    private double bear_age;
    private String bear_type;
    private Map<String, String> properties = new HashMap<>();

    @JsonAnyGetter
    public Map<String, String> getMap() {
        return properties;
    }

    @JsonAnySetter
    public void setMap(String name, String value) {
        properties.put(name, value);
    }

    public BearPOJO(String bear_type, String bear_name, double bear_age) {
        this.bear_name = bear_name;
        this.bear_age = bear_age;
        this.bear_type = bear_type;
    }

    public BearPOJO() {
    }

    public String getBear_type() {
        return bear_type;
    }

    public String getBear_name() {
        return bear_name;
    }

    public double getBear_age() {
        return bear_age;
    }

    public void setBear_type(String bear_type) {
        this.bear_type = bear_type;
    }

    public void setBear_name(String bear_name) {
        this.bear_name = bear_name;
    }

    public void setBear_age(double bear_age) {
        this.bear_age = bear_age;
    }
    public String toString() {
        return "My bear is a " + this.bear_type + " " + this.bear_name + " " + this.bear_age;
    }
}
