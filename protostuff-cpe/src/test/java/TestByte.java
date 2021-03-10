import io.protostuff.CpeIOUtil;
import io.protostuff.LinkedBuffer;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

import java.io.IOException;

public class TestByte {
    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }


    static void roundTrip() throws IOException {
        Course foo = new Course((byte) 1, 10);
        // this is lazily created and cached by RuntimeSchema
        // so its safe to call RuntimeSchema.getSchema(Foo.class) over and over
        // The getSchema method is also thread-safe
        Schema<Course> schema = RuntimeSchema.getSchema(Course.class);

        // Re-use (manage) this buffer to avoid allocating on every serialization
        LinkedBuffer buffer = LinkedBuffer.allocate(512);

        // ser
        // ser
        final byte[] protostuff;
        try
        {
            protostuff = CpeIOUtil.toByteArray(foo, schema, buffer);
            System.out.println(bytesToHex(protostuff));
            Course fooParsed = schema.newMessage();
            CpeIOUtil.mergeFrom(protostuff, fooParsed, schema);
            System.out.println(fooParsed);
        }catch (Exception e){
            e.printStackTrace();
        }

        finally
        {
            buffer.clear();
        }
    }

    public static void main(String[] args) throws IOException {
        roundTrip();
    }
}
