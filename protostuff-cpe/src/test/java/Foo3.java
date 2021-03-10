import io.protostuff.ArrayDetail;
import io.protostuff.ArraySizeType;

import java.util.ArrayList;
import java.util.List;

public class Foo3 extends Foo{

    @ArrayDetail(sizeType = ArraySizeType.FIXED, length = 5, size = 2)
    private List<Course> courses = new ArrayList<>();
    private float rate = 1.2f;

    public Foo3(short name, int age) {
        super(name, age);
    }

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
                ", name=" + name +
                ", age=" + age +
                '}';
    }
}