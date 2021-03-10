import io.protostuff.ArrayDetail;
import io.protostuff.ArraySizeType;

import java.util.ArrayList;
import java.util.List;

public class Foo4 extends Foo{

    private short courseSize;

    @ArrayDetail(sizeType = ArraySizeType.FIELD_DEFINE, length = 5, sizeField = "courseSize")
    private List<Course> courses = new ArrayList<>();
    private float rate = 1.2f;

    public Foo4(short name, int age) {
        super(name, age);
    }

    public List<Course> getCourses() {
        return courses;
    }

    public short getCourseSize() {
        return courseSize;
    }

    public void setCourseSize(short courseSize) {
        this.courseSize = courseSize;
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