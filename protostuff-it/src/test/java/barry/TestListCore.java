package barry;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

import java.io.IOException;

public class TestListCore {
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
        Foo3 foo = new Foo3();
        Course course1 = new Course((byte) 1, 10);
        Course course2 = new Course((byte) 2, 20);
        foo.getCourses().add(course1);
        foo.getCourses().add(course2);
        Schema<Foo3> schema = RuntimeSchema.getSchema(Foo3.class);

        // Re-use (manage) this buffer to avoid allocating on every serialization
        LinkedBuffer buffer = LinkedBuffer.allocate(512);

        // ser
        final byte[] protostuff;
        try
        {
            protostuff = ProtostuffIOUtil.toByteArray(foo, schema, buffer);
            System.out.println(bytesToHex(protostuff));
            Foo3 fooParsed = schema.newMessage();
            ProtostuffIOUtil.mergeFrom(protostuff, fooParsed, schema);
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
