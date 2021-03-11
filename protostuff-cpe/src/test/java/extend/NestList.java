package extend;

import io.protostuff.ArrayDetail;
import io.protostuff.ArraySizeType;

import java.util.ArrayList;
import java.util.List;

public class NestList {

    private byte before;
    @ArrayDetail(sizeType = ArraySizeType.FIXED, size = 2)
    private List<ItemList> itemLists = new ArrayList<>();
    private byte after;

    public byte getBefore() {
        return before;
    }

    public void setBefore(byte before) {
        this.before = before;
    }

    public List<ItemList> getItemLists() {
        return itemLists;
    }

    public void setItemLists(List<ItemList> itemLists) {
        this.itemLists = itemLists;
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

        NestList nestList = (NestList) o;

        if (before != nestList.before) return false;
        if (after != nestList.after) return false;
        return itemLists != null ? itemLists.equals(nestList.itemLists) : nestList.itemLists == null;
    }

    @Override
    public int hashCode() {
        int result = before;
        result = 31 * result + (itemLists != null ? itemLists.hashCode() : 0);
        result = 31 * result + (int) after;
        return result;
    }

    @Override
    public String toString() {
        return "NestList{" +
                "before=" + before +
                ", itemLists=" + itemLists +
                ", after=" + after +
                '}';
    }
}