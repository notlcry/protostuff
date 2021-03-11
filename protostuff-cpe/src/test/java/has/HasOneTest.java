package has;

import builtIn.HexUtil;
import io.protostuff.AbstractTest;
import io.protostuff.CpeIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

import java.io.IOException;

/**
 * Tests for object field
 *
 * @author huiyu
 * @created
 */
public class HasOneTest extends AbstractTest {
    static HasOne generatePojo() {
        HasOne foo = new HasOne();
        foo.setAfter((short) 4);
        foo.setBefore((short) 1);
        foo.setFoo(new Foo((short) 2,3));
        return foo;
    }

    static void verifyCpe(HasOne p) throws IOException {
        Schema<HasOne> schema = RuntimeSchema.getSchema(HasOne.class);
        byte[] data = CpeIOUtil.toByteArray(p, schema, buf());
        HasOne pFromByteArray = new HasOne();
        CpeIOUtil.mergeFrom(data, pFromByteArray, schema);
        assertEquals(p, pFromByteArray);
    }

    public void testCpe() throws IOException {
        verifyCpe(generatePojo());
    }

    public void testHex(){
        HasOne pojo = generatePojo();
        Schema<HasOne> schema = RuntimeSchema.getSchema(HasOne.class);
        byte[] data = CpeIOUtil.toByteArray(pojo, schema, buf());

        String expected = "0001000200000000000000030004";
        assertEquals(expected, HexUtil.bytesToHex(data));
    }
}
