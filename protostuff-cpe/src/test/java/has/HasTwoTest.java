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
public class HasTwoTest extends AbstractTest {
    static HasTwo generatePojo() {
        HasTwo hasTwo = new HasTwo();
        hasTwo.setBefore((short) 1);
        hasTwo.setFoo(new Foo((short) 2,3));
        hasTwo.setFoo2(new Foo2("test", (short) 4));
        hasTwo.setAfter((short) 5);
        return hasTwo;
    }

    static void verifyCpe(HasTwo p) throws IOException {
        Schema<HasTwo> schema = RuntimeSchema.getSchema(HasTwo.class);
        byte[] data = CpeIOUtil.toByteArray(p, schema, buf());
        HasTwo pFromByteArray = new HasTwo();
        CpeIOUtil.mergeFrom(data, pFromByteArray, schema);
        assertEquals(p, pFromByteArray);
    }

    public void testCpe() throws IOException {
        verifyCpe(generatePojo());
    }

    public void testHex(){
        HasTwo pojo = generatePojo();
        Schema<HasTwo> schema = RuntimeSchema.getSchema(HasTwo.class);
        byte[] data = CpeIOUtil.toByteArray(pojo, schema, buf());

        String expected = "0001000200000000000000037465737400040005";
        assertEquals(expected, HexUtil.bytesToHex(data));
    }
}
