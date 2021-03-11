package extend;

import io.protostuff.ArrayDetail;
import io.protostuff.ArraySizeType;

import java.util.ArrayList;
import java.util.List;

public class ItemList {

    private byte before;
    @ArrayDetail(sizeType = ArraySizeType.FIXED, size = 2)
    private List<Course> courses = new ArrayList<>();
    private byte after;

    public byte getBefore() {
        return before;
    }

    public void setBefore(byte before) {
        this.before = before;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public byte getAfter() {
        return after;
    }

    public void setAfter(byte after) {
        this.after = after;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemList itemList = (ItemList) o;

        if (before != itemList.before) return false;
        if (after != itemList.after) return false;
        return courses != null ? courses.equals(itemList.courses) : itemList.courses == null;
    }

    @Override
    public int hashCode() {
        int result = before;
        result = 31 * result + (courses != null ? courses.hashCode() : 0);
        result = 31 * result + (int) after;
        return result;
    }

    @Override
    public String toString() {
        return "ItemList{" +
                "before=" + before +
                ", courses=" + courses +
                ", after=" + after +
                '}';
    }
}