package bear;

public class BearPOJO {

    private String bear_name;
    private double bear_age;
    private String bear_type;

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
