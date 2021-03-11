package extend;

import builtIn.HexUtil;
import io.protostuff.AbstractTest;
import io.protostuff.CpeIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Tests for byte field
 *
 * @author huiyu
 * @created
 */
public class WithNestListTest extends AbstractTest {
    static NestList generatePojo() {
        NestList nestList = new NestList();
        nestList.setBefore((byte) 1);
        nestList.setItemLists(new ArrayList<ItemList>());
        ItemList itemList1 = new ItemList();
        {
            itemList1.setBefore((byte) 2);
            Course course1 = new Course((byte) 3, 4);
            Course course2 = new Course((byte) 5, 6);
            itemList1.setCourses(new ArrayList<Course>());
            itemList1.getCourses().add(course1);
            itemList1.getCourses().add(course2);
            itemList1.setAfter((byte) 7);
        }

        ItemList itemList2 = new ItemList();
        {
            itemList2.setBefore((byte) 8);
            Course course1 = new Course((byte) 9, 10);
            Course course2 = new Course((byte) 11, 12);
            itemList2.setCourses(new ArrayList<Course>());
            itemList2.getCourses().add(course1);
            itemList2.getCourses().add(course2);
            itemList2.setAfter((byte) 13);
        }
        nestList.getItemLists().add(itemList1);
        nestList.getItemLists().add(itemList2);
        nestList.setAfter((byte) 14);
        return nestList;
    }

    static void verifyCpe(NestList p) throws IOException {
        Schema<NestList> schema = RuntimeSchema.getSchema(NestList.class);
        byte[] data = CpeIOUtil.toByteArray(p, schema, buf());
        NestList pFromByteArray = new NestList();
        CpeIOUtil.mergeFrom(data, pFromByteArray, schema);
        assertEquals(p, pFromByteArray);
    }

    public void testCpe() throws IOException {
        verifyCpe(generatePojo());
    }

    public void testHex(){
        NestList pojo = generatePojo();
        Schema<NestList> schema = RuntimeSchema.getSchema(NestList.class);
        byte[] data = CpeIOUtil.toByteArray(pojo, schema, buf());

        String expected = "0102030000000405000000060708090000000A0B0000000C0D0E";
        assertEquals(expected, HexUtil.bytesToHex(data));
    }
}
