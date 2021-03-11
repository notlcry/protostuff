package has;

public class HasTwo {

    private short before;
    private Foo foo;
    private Foo2 foo2;
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

    public Foo2 getFoo2() {
        return foo2;
    }

    public void setFoo2(Foo2 foo2) {
        this.foo2 = foo2;
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

        HasTwo hasTwo = (HasTwo) o;

        if (before != hasTwo.before) return false;
        if (after != hasTwo.after) return false;
        if (foo != null ? !foo.equals(hasTwo.foo) : hasTwo.foo != null) return false;
        return foo2 != null ? foo2.equals(hasTwo.foo2) : hasTwo.foo2 == null;
    }

    @Override
    public int hashCode() {
        int result = before;
        result = 31 * result + (foo != null ? foo.hashCode() : 0);
        result = 31 * result + (foo2 != null ? foo2.hashCode() : 0);
        result = 31 * result + (int) after;
        return result;
    }

    @Override
    public String toString() {
        return "HasTwo{" +
                "before=" + before +
                ", foo=" + foo +
                ", foo2=" + foo2 +
                ", after=" + after +
                '}';
    }
}