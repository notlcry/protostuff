package builtIn;

/**
 * @author huiyu
 * @created 2021/3/10
 */
public class PojoInt {
    private int someInt32;
    private int someUint32;
    private int someSint32;
    private int someFixed32;
    private int someSfixed32;
    private long someInt64;
    private long someUint64;
    private long someSint64;
    private long someFixed64;
    private long someSfixed64;

    public int getSomeInt32() {
        return someInt32;
    }

    public void setSomeInt32(int someInt32) {
        this.someInt32 = someInt32;
    }

    public int getSomeUint32() {
        return someUint32;
    }

    public void setSomeUint32(int someUint32) {
        this.someUint32 = someUint32;
    }

    public int getSomeSint32() {
        return someSint32;
    }

    public void setSomeSint32(int someSint32) {
        this.someSint32 = someSint32;
    }

    public int getSomeFixed32() {
        return someFixed32;
    }

    public void setSomeFixed32(int someFixed32) {
        this.someFixed32 = someFixed32;
    }

    public int getSomeSfixed32() {
        return someSfixed32;
    }

    public void setSomeSfixed32(int someSfixed32) {
        this.someSfixed32 = someSfixed32;
    }

    public long getSomeInt64() {
        return someInt64;
    }

    public void setSomeInt64(long someInt64) {
        this.someInt64 = someInt64;
    }

    public long getSomeUint64() {
        return someUint64;
    }

    public void setSomeUint64(long someUint64) {
        this.someUint64 = someUint64;
    }

    public long getSomeSint64() {
        return someSint64;
    }

    public void setSomeSint64(long someSint64) {
        this.someSint64 = someSint64;
    }

    public long getSomeFixed64() {
        return someFixed64;
    }

    public void setSomeFixed64(long someFixed64) {
        this.someFixed64 = someFixed64;
    }

    public long getSomeSfixed64() {
        return someSfixed64;
    }

    public void setSomeSfixed64(long someSfixed64) {
        this.someSfixed64 = someSfixed64;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PojoInt pojoInt = (PojoInt) o;

        if (someInt32 != pojoInt.someInt32) return false;
        if (someUint32 != pojoInt.someUint32) return false;
        if (someSint32 != pojoInt.someSint32) return false;
        if (someFixed32 != pojoInt.someFixed32) return false;
        if (someSfixed32 != pojoInt.someSfixed32) return false;
        if (someInt64 != pojoInt.someInt64) return false;
        if (someUint64 != pojoInt.someUint64) return false;
        if (someSint64 != pojoInt.someSint64) return false;
        if (someFixed64 != pojoInt.someFixed64) return false;
        return someSfixed64 == pojoInt.someSfixed64;
    }

    @Override
    public int hashCode() {
        int result = someInt32;
        result = 31 * result + someUint32;
        result = 31 * result + someSint32;
        result = 31 * result + someFixed32;
        result = 31 * result + someSfixed32;
        result = 31 * result + (int) (someInt64 ^ (someInt64 >>> 32));
        result = 31 * result + (int) (someUint64 ^ (someUint64 >>> 32));
        result = 31 * result + (int) (someSint64 ^ (someSint64 >>> 32));
        result = 31 * result + (int) (someFixed64 ^ (someFixed64 >>> 32));
        result = 31 * result + (int) (someSfixed64 ^ (someSfixed64 >>> 32));
        return result;
    }
}
