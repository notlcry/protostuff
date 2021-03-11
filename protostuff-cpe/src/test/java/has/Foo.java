package has;

public class Foo
{
    short name;
    long age;

    public Foo(short name, int age)
    {
        this.name = name;
        this.age = age;
    }

    public Foo() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Foo foo = (Foo) o;

        if (name != foo.name) return false;
        return age == foo.age;
    }

    @Override
    public int hashCode() {
        int result = name;
        result = 31 * result + (int) (age ^ (age >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Foo{" +
                "name=" + name +
                ", age=" + age +
                '}';
    }
}