package extend;

import builtIn.HexUtil;
import builtIn.PojoShort;
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
public class WithFixedListTest extends AbstractTest {
    static Foo3 generatePojo() {
        Foo3 foo = new Foo3((short) 1,2);
        Course course1 = new Course((byte) 1, 10);
        Course course2 = new Course((byte) 2, 20);
        foo.getCourses().add(course1);
        foo.getCourses().add(course2);
        return foo;
    }

    static void verifyCpe(Foo3 p) throws IOException {
        Schema<Foo3> schema = RuntimeSchema.getSchema(Foo3.class);
        byte[] data = CpeIOUtil.toByteArray(p, schema, buf());
        Foo3 pFromByteArray = new Foo3();
        CpeIOUtil.mergeFrom(data, pFromByteArray, schema);
        assertEquals(p, pFromByteArray);
    }

    public void testCpe() throws IOException {
        verifyCpe(generatePojo());
    }

    public void testHex(){
        Foo3 pojo = generatePojo();
        Schema<Foo3> schema = RuntimeSchema.getSchema(Foo3.class);
        byte[] data = CpeIOUtil.toByteArray(pojo, schema, buf());

        String expected = "00010000000000000002010000000A020000001400000000";
        assertEquals(expected, HexUtil.bytesToHex(data));
    }
}
