package builtIn;

/**
 * @author huiyu
 * @created 2021/3/10
 */
public class PojoFloat {
    private float someFloat;
    private float someNFloat;
    private double someDouble;
    private double someNDouble;

    public float getSomeFloat() {
        return someFloat;
    }

    public void setSomeFloat(float someFloat) {
        this.someFloat = someFloat;
    }

    public float getSomeNFloat() {
        return someNFloat;
    }

    public void setSomeNFloat(float someNFloat) {
        this.someNFloat = someNFloat;
    }

    public double getSomeDouble() {
        return someDouble;
    }

    public void setSomeDouble(double someDouble) {
        this.someDouble = someDouble;
    }

    public double getSomeNDouble() {
        return someNDouble;
    }

    public void setSomeNDouble(double someNDouble) {
        this.someNDouble = someNDouble;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PojoFloat pojoFloat = (PojoFloat) o;

        if (Float.compare(pojoFloat.someFloat, someFloat) != 0) return false;
        if (Float.compare(pojoFloat.someNFloat, someNFloat) != 0) return false;
        if (Double.compare(pojoFloat.someDouble, someDouble) != 0) return false;
        return Double.compare(pojoFloat.someNDouble, someNDouble) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (someFloat != +0.0f ? Float.floatToIntBits(someFloat) : 0);
        result = 31 * result + (someNFloat != +0.0f ? Float.floatToIntBits(someNFloat) : 0);
        temp = Double.doubleToLongBits(someDouble);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(someNDouble);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
