package builtIn;

/**
 * @author huiyu
 * @created 2021/3/10
 */
public class PojoByte {
    private byte someByte;
    private byte someNByte;

    public byte getSomeByte() {
        return someByte;
    }

    public void setSomeByte(byte someByte) {
        this.someByte = someByte;
    }

    public byte getSomeNByte() {
        return someNByte;
    }

    public void setSomeNByte(byte someNByte) {
        this.someNByte = someNByte;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PojoByte pojoByte = (PojoByte) o;

        if (someByte != pojoByte.someByte) return false;
        return someNByte == pojoByte.someNByte;
    }

    @Override
    public int hashCode() {
        int result = someByte;
        result = 31 * result + (int) someNByte;
        return result;
    }
}
