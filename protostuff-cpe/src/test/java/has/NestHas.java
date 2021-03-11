package has;

/**
 * @author huiyu
 * @created 2021/3/11
 */
public class NestHas {
    private byte before;
    private HasOne hasOne;
    private byte after;

    public byte getBefore() {
        return before;
    }

    public void setBefore(byte before) {
        this.before = before;
    }

    public HasOne getHasOne() {
        return hasOne;
    }

    public void setHasOne(HasOne hasOne) {
        this.hasOne = hasOne;
    }

    public byte getAfter() {
        return after;
    }

    public void setAfter(byte after) {
        this.after = after;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NestHas multiHas = (NestHas) o;

        if (before != multiHas.before) return false;
        if (after != multiHas.after) return false;
        return hasOne != null ? hasOne.equals(multiHas.hasOne) : multiHas.hasOne == null;
    }

    @Override
    public int hashCode() {
        int result = before;
        result = 31 * result + (hasOne != null ? hasOne.hashCode() : 0);
        result = 31 * result + (int) after;
        return result;
    }

    @Override
    public String toString() {
        return "NestHas{" +
                "before=" + before +
                ", hasOne=" + hasOne +
                ", after=" + after +
                '}';
    }
}
