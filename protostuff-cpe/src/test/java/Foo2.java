import io.protostuff.FixedLength;

import java.util.Arrays;

public class Foo2 extends Foo {

    @FixedLength(2)
    byte[] habit;

    @FixedLength(10)
    String vid = "123";

    float rate = 1.2f;

    public Foo2(short name, int age, byte[] habit) {
        super(name, age);
        this.habit = habit;
    }

    @Override
    public String toString() {
        return "Foo2{" +
                "habit=" + Arrays.toString(habit) +
                ", vid='" + vid + '\'' +
                ", rate=" + rate +
                ", name=" + name +
                ", age=" + age +
                '}';
    }
}