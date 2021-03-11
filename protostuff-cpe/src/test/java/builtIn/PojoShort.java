package builtIn;

/**
 * @author huiyu
 * @created 2021/3/10
 */
public class PojoShort {
    private short someInt16;
    private short someNInt16;

    public short getSomeInt16() {
        return someInt16;
    }

    public void setSomeInt16(short someInt16) {
        this.someInt16 = someInt16;
    }

    public short getSomeNInt16() {
        return someNInt16;
    }

    public void setSomeNInt16(short someNInt16) {
        this.someNInt16 = someNInt16;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PojoShort pojoShort = (PojoShort) o;

        if (someInt16 != pojoShort.someInt16) return false;
        return someNInt16 == pojoShort.someNInt16;
    }

    @Override
    public int hashCode() {
        int result = someInt16;
        result = 31 * result + (int) someNInt16;
        return result;
    }
}
