package extend;

public class Course {
    private byte id;
    private int source;

    public Course(byte id, int source) {
        this.id = id;
        this.source = source;
    }

    public byte getId() {
        return id;
    }

    public void setId(byte id) {
        this.id = id;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "has.Course{" +
                "id=" + id +
                ", source=" + source +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        if (id != course.id) return false;
        return source == course.source;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + source;
        return result;
    }
}
