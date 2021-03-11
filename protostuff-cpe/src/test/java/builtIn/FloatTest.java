package builtIn;//========================================================================
//Copyright 2007-2011 David Yu dyuproject@gmail.com
//------------------------------------------------------------------------
//Licensed under the Apache License, Version 2.0 (the "License");
//you may not use this file except in compliance with the License.
//You may obtain a copy of the License at 
//http://www.apache.org/licenses/LICENSE-2.0
//Unless required by applicable law or agreed to in writing, software
//distributed under the License is distributed on an "AS IS" BASIS,
//WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//See the License for the specific language governing permissions and
//limitations under the License.
//========================================================================

import builtIn.HexUtil;
import builtIn.PojoFloat;
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
public class FloatTest extends AbstractTest {
    static PojoFloat filledValue(float i, float ni, double j, double nj) {
        assertTrue(i >= 0);
        assertTrue(j >= 0);

        PojoFloat p = new PojoFloat();
        p.setSomeFloat(i);
        p.setSomeNFloat(ni);
        p.setSomeDouble(j);
        p.setSomeNDouble(nj);
        return p;
    }

    static void verifyCpe(PojoFloat p) throws IOException {
        Schema<PojoFloat> schema = RuntimeSchema.getSchema(PojoFloat.class);
        byte[] data = CpeIOUtil.toByteArray(p, schema, buf());

        PojoFloat pFromByteArray = new PojoFloat();
        CpeIOUtil.mergeFrom(data, pFromByteArray, schema);

        assertEquals(p, pFromByteArray);
    }

    public void testCpe() throws IOException {
        verifyCpe(filledValue(0, 0, 0, 0));
        verifyCpe(filledValue((float) 1.5, 1, 1.5, 1));
        verifyCpe(filledValue((float) 1.5, (float) -1.5, 1.5, -1.5));
        verifyCpe(filledValue(Integer.MAX_VALUE, Integer.MAX_VALUE,
                Long.MAX_VALUE, Long.MAX_VALUE));
        verifyCpe(filledValue(Integer.MAX_VALUE, Integer.MIN_VALUE,
                Long.MAX_VALUE, Long.MIN_VALUE));
    }

    public void testHex(){
        PojoFloat pojo = filledValue((float) 1.5, (float) -1.5, 1.5, -1.5);
        Schema<PojoFloat> schema = RuntimeSchema.getSchema(PojoFloat.class);
        byte[] data = CpeIOUtil.toByteArray(pojo, schema, buf());

        String expected = "3FC00000BFC000003FF8000000000000BFF8000000000000";
        assertEquals(expected, HexUtil.bytesToHex(data));
    }
}
