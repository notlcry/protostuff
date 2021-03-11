package extend;

import io.protostuff.ArrayDetail;
import io.protostuff.ArraySizeType;

import java.util.ArrayList;
import java.util.List;

public class Foo3 extends Foo{

    @ArrayDetail(sizeType = ArraySizeType.FIXED, size = 2)
    private List<Course> courses = new ArrayList<>();
    private float rate;

    public Foo3(short name, int age) {
        super(name, age);
    }

    public Foo3() {
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
        return "has.Foo3{" +
                "courses=" + courses +
                ", rate=" + rate +
                ", name=" + name +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Foo3 foo3 = (Foo3) o;

        if (Float.compare(foo3.rate, rate) != 0) return false;
        return courses != null ? courses.equals(foo3.courses) : foo3.courses == null;
    }

    @Override
    public int hashCode() {
        int result = courses != null ? courses.hashCode() : 0;
        result = 31 * result + (rate != +0.0f ? Float.floatToIntBits(rate) : 0);
        return result;
    }
}