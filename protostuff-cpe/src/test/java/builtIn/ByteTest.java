package builtIn;

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
public class ByteTest extends AbstractTest {
    static PojoByte filledValue(byte i, byte ni) {
        assertTrue(i >= 0);

        PojoByte p = new PojoByte();
        p.setSomeByte(i);
        p.setSomeNByte(ni);
        return p;
    }

    static void verifyCpe(PojoByte p) throws IOException {
        Schema<PojoByte> schema = RuntimeSchema.getSchema(PojoByte.class);
        byte[] data = CpeIOUtil.toByteArray(p, schema, buf());

        PojoByte pFromByteArray = new PojoByte();
        CpeIOUtil.mergeFrom(data, pFromByteArray, schema);

        assertEquals(p, pFromByteArray);
    }

    public void testCpe() throws IOException {
        verifyCpe(filledValue((byte)0, (byte) 0));
        verifyCpe(filledValue((byte)1, (byte) -1));
        verifyCpe(filledValue(Byte.MAX_VALUE, Byte.MIN_VALUE));
    }

    public void testHex(){
        PojoByte pojo = filledValue((byte)1, (byte) -1);
        Schema<PojoByte> schema = RuntimeSchema.getSchema(PojoByte.class);
        byte[] data = CpeIOUtil.toByteArray(pojo, schema, buf());

        String expected = "01FF";
        assertEquals(expected, HexUtil.bytesToHex(data));
    }
}
