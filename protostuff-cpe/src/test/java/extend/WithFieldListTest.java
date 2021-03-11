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
public class WithFieldListTest extends AbstractTest {
    static Foo4 generatePojo() {
        Foo4 foo = new Foo4((short) 1,2);
        Course course1 = new Course((byte) 1, 10);
        Course course2 = new Course((byte) 2, 20);
        Course course3 = new Course((byte) 3, 30);
        foo.getCourses().add(course1);
        foo.getCourses().add(course2);
        foo.getCourses().add(course3);
        foo.setCourseSize((short) 3);
        return foo;
    }

    static void verifyCpe(Foo4 p) throws IOException {
        Schema<Foo4> schema = RuntimeSchema.getSchema(Foo4.class);
        byte[] data = CpeIOUtil.toByteArray(p, schema, buf());
        Foo4 pFromByteArray = new Foo4();
        CpeIOUtil.mergeFrom(data, pFromByteArray, schema);
        assertEquals(p, pFromByteArray);
    }

    public void testCpe() throws IOException {
        verifyCpe(generatePojo());
    }

    public void testHex(){
        Foo4 pojo = generatePojo();
        Schema<Foo4> schema = RuntimeSchema.getSchema(Foo4.class);
        byte[] data = CpeIOUtil.toByteArray(pojo, schema, buf());

        String expected = "000100000000000000020003010000000A0200000014030000001E00000000";
        assertEquals(expected, HexUtil.bytesToHex(data));
    }
}
