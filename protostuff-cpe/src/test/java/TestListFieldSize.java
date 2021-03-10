import io.protostuff.CpeIOUtil;
import io.protostuff.LinkedBuffer;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

import java.io.IOException;

public class TestListFieldSize {
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
        Foo4 foo = new Foo4((short) 1,2);
        Course course1 = new Course((byte) 1, 10);
        Course course2 = new Course((byte) 2, 20);
        Course course3 = new Course((byte) 3, 30);
        foo.getCourses().add(course1);
        foo.getCourses().add(course2);
        foo.getCourses().add(course3);
        foo.setCourseSize((short) 3);
        Schema<? extends Foo> schema = RuntimeSchema.getSchema(foo.getClass());

        // Re-use (manage) this buffer to avoid allocating on every serialization
        LinkedBuffer buffer = LinkedBuffer.allocate(512);

        // ser
        final byte[] protostuff;
        try
        {
            protostuff = CpeIOUtil.toByteArray(foo, schema, buffer);
            System.out.println(bytesToHex(protostuff));
            Foo4 fooParsed = (Foo4) schema.newMessage();
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
