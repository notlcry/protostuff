package barry;

import java.util.ArrayList;
import java.util.List;

public class Foo3{

    private List<Course> courses = new ArrayList<>();
    private float rate = 1.2f;

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Foo3{" +
                "courses=" + courses +
                ", rate=" + rate +
                '}';
    }
}