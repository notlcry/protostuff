package barry;

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
        return "Course{" +
                "id=" + id +
                ", source=" + source +
                '}';
    }
}
