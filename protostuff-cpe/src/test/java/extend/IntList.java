package extend;

import io.protostuff.ArrayDetail;
import io.protostuff.ArraySizeType;

import java.util.ArrayList;
import java.util.List;

public class IntList extends Foo{

    @ArrayDetail(sizeType = ArraySizeType.FIXED, size = 2)
    private List<Integer> courses = new ArrayList<>();
    private float rate;

    public IntList(short name, int age) {
        super(name, age);
    }

    public IntList() {
    }

    public List<Integer> getCourses() {
        return courses;
    }

    public void setCourses(List<Integer> courses) {
        this.courses = courses;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        IntList intList = (IntList) o;

        if (Float.compare(intList.rate, rate) != 0) return false;
        return courses != null ? courses.equals(intList.courses) : intList.courses == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (courses != null ? courses.hashCode() : 0);
        result = 31 * result + (rate != +0.0f ? Float.floatToIntBits(rate) : 0);
        return result;
    }
}