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
public class NestHasTest extends AbstractTest {
    static NestHas generatePojo() {

        NestHas nestHas = new NestHas();
        nestHas.setBefore((byte) 1);

        HasOne foo = new HasOne();
        foo.setBefore((short) 2);
        foo.setFoo(new Foo((short) 3,4));
        foo.setAfter((short) 5);
        nestHas.setHasOne(foo);
        nestHas.setAfter((byte) 6);
        return nestHas;
    }

    static void verifyCpe(NestHas p) throws IOException {
        Schema<NestHas> schema = RuntimeSchema.getSchema(NestHas.class);
        byte[] data = CpeIOUtil.toByteArray(p, schema, buf());
        NestHas pFromByteArray = new NestHas();
        CpeIOUtil.mergeFrom(data, pFromByteArray, schema);
        assertEquals(p, pFromByteArray);
    }

    public void testCpe() throws IOException {
        verifyCpe(generatePojo());
    }

    public void testHex(){
        NestHas pojo = generatePojo();
        Schema<NestHas> schema = RuntimeSchema.getSchema(NestHas.class);
        byte[] data = CpeIOUtil.toByteArray(pojo, schema, buf());

        String expected = "01000200030000000000000004000506";
        assertEquals(expected, HexUtil.bytesToHex(data));
    }
}
