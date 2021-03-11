package has;

public class HasOne {

    private short before;
    private Foo foo;
    private short after;

    public short getBefore() {
        return before;
    }

    public void setBefore(short before) {
        this.before = before;
    }

    public Foo getFoo() {
        return foo;
    }

    public void setFoo(Foo foo) {
        this.foo = foo;
    }

    public short getAfter() {
        return after;
    }

    public void setAfter(short after) {
        this.after = after;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HasOne hasOne = (HasOne) o;

        if (before != hasOne.before) return false;
        if (after != hasOne.after) return false;
        return foo != null ? foo.equals(hasOne.foo) : hasOne.foo == null;
    }

    @Override
    public int hashCode() {
        int result = before;
        result = 31 * result + (foo != null ? foo.hashCode() : 0);
        result = 31 * result + (int) after;
        return result;
    }

    @Override
    public String toString() {
        return "HasOne{" +
                "before=" + before +
                ", foo=" + foo +
                ", after=" + after +
                '}';
    }
}