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
public class ShortTest extends AbstractTest {
    static PojoShort filledValue(short i, short ni) {
        assertTrue(i >= 0);

        PojoShort p = new PojoShort();
        p.setSomeInt16(i);
        p.setSomeNInt16(ni);
        return p;
    }

    static void verifyCpe(PojoShort p) throws IOException {
        Schema<PojoShort> schema = RuntimeSchema.getSchema(PojoShort.class);
        byte[] data = CpeIOUtil.toByteArray(p, schema, buf());

        PojoShort pFromByteArray = new PojoShort();
        CpeIOUtil.mergeFrom(data, pFromByteArray, schema);

        assertEquals(p, pFromByteArray);
    }

    public void testCpe() throws IOException {
        verifyCpe(filledValue((short)0, (short) 0));
        verifyCpe(filledValue((short)1, (short) -1));
        verifyCpe(filledValue(Short.MAX_VALUE, Short.MIN_VALUE));
    }

    public void testHex(){
        PojoShort pojo = filledValue((byte)1, (byte) -1);
        Schema<PojoShort> schema = RuntimeSchema.getSchema(PojoShort.class);
        byte[] data = CpeIOUtil.toByteArray(pojo, schema, buf());

        String expected = "0001FFFF";
        assertEquals(expected, HexUtil.bytesToHex(data));
    }
}
