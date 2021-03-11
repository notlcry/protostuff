package has;

import io.protostuff.FixedLength;

public class Foo2 {
    @FixedLength(4)
    private String luke;
    private short level;

    public Foo2() {
    }

    public Foo2(String luke, short level) {
        this.luke = luke;
        this.level = level;
    }

    public String getLuke() {
        return luke;
    }

    public void setLuke(String luke) {
        this.luke = luke;
    }

    public short getLevel() {
        return level;
    }

    public void setLevel(short level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Foo2 foo2 = (Foo2) o;

        if (level != foo2.level) return false;
        return luke != null ? luke.equals(foo2.luke) : foo2.luke == null;
    }

    @Override
    public int hashCode() {
        int result = luke != null ? luke.hashCode() : 0;
        result = 31 * result + (int) level;
        return result;
    }

    @Override
    public String toString() {
        return "Foo2{" +
                "luke='" + luke + '\'' +
                ", level=" + level +
                '}';
    }
}