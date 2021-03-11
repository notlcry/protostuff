package extend;

import builtIn.HexUtil;
import io.protostuff.AbstractTest;
import io.protostuff.CpeIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

import java.io.IOException;

/**
 * Tests for byte field
 *
 * @author huiyu
 * @created
 */
public class WithIntListTest extends AbstractTest {
    static IntList generatePojo() {
        IntList foo = new IntList((short) 1,2);
        foo.getCourses().add(1);
        foo.getCourses().add(2);
        return foo;
    }

    static void verifyCpe(IntList p) throws IOException {
        Schema<IntList> schema = RuntimeSchema.getSchema(IntList.class);
        byte[] data = CpeIOUtil.toByteArray(p, schema, buf());
        IntList pFromByteArray = new IntList();
        CpeIOUtil.mergeFrom(data, pFromByteArray, schema);
        assertEquals(p, pFromByteArray);
    }

    public void testCpe() throws IOException {
        verifyCpe(generatePojo());
    }

    public void testHex(){
        IntList pojo = generatePojo();
        Schema<IntList> schema = RuntimeSchema.getSchema(IntList.class);
        byte[] data = CpeIOUtil.toByteArray(pojo, schema, buf());

        String expected = "00010000000000000002000000010000000200000000";
        assertEquals(expected, HexUtil.bytesToHex(data));
    }
}
