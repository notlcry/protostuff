package builtIn;

import io.protostuff.AbstractTest;
import io.protostuff.CpeIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

import java.io.IOException;

/**
 * Tests for int field
 *
 * @author huiyu
 * @created
 */
public class IntTest extends AbstractTest {
    static PojoInt filledValue(int i, int ni, long j, long nj) {
        assertTrue(i >= 0);
        assertTrue(j >= 0);

        PojoInt p = new PojoInt();
        p.setSomeInt32(i);
        p.setSomeUint32(i);
        p.setSomeFixed32(i);
        p.setSomeSint32(ni);
        p.setSomeSfixed32(ni);

        p.setSomeInt64(j);
        p.setSomeUint64(j);
        p.setSomeFixed64(j);
        p.setSomeSint64(nj);
        p.setSomeSfixed64(nj);
        return p;
    }

    static void verifyCpe(PojoInt p) throws IOException {
        Schema<PojoInt> schema = RuntimeSchema.getSchema(PojoInt.class);
        byte[] data = CpeIOUtil.toByteArray(p, schema, buf());

        PojoInt pFromByteArray = new PojoInt();
        CpeIOUtil.mergeFrom(data, pFromByteArray, schema);

        assertEquals(p, pFromByteArray);
    }

    public void testCpe() throws IOException {
        verifyCpe(filledValue(0, 0, 0, 0));
        verifyCpe(filledValue(1, 1, 1, 1));
        verifyCpe(filledValue(1, -1, 1, -1));
        verifyCpe(filledValue(Integer.MAX_VALUE, Integer.MAX_VALUE,
                Long.MAX_VALUE, Long.MAX_VALUE));
        verifyCpe(filledValue(Integer.MAX_VALUE, Integer.MIN_VALUE,
                Long.MAX_VALUE, Long.MIN_VALUE));
    }

    public void testHex(){
        PojoInt pojo = filledValue(0, 0, 0, Long.MAX_VALUE);
        Schema<PojoInt> schema = RuntimeSchema.getSchema(PojoInt.class);
        byte[] data = CpeIOUtil.toByteArray(pojo, schema, buf());

        String expected = "0000000000000000000000000000000000000000000000000000000000000000000000007FFFFFFFFFFFFFFF00000000000000007FFFFFFFFFFFFFFF";
        assertEquals(expected, HexUtil.bytesToHex(data));
    }
}
