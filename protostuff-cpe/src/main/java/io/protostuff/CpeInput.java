//========================================================================
//Copyright 2007-2010 David Yu dyuproject@gmail.com
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

package io.protostuff;

import io.protostuff.StringSerializer.STRING;
import io.protostuff.runtime.RuntimeSchema;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Reads and decodes protocol buffer message fields from an internal byte array buffer. This object is re-usable via
 * doing a reset on the byte array position and length. This is used internally by {@link } where it catches
 * {@link ArrayIndexOutOfBoundsException} when a message is truncated.
 *
 * @author David Yu
 * @created Jun 22, 2010
 */
public final class CpeInput implements Input {

    private final ByteBuffer buffer;
    // private final byte[] buffer;
    private int lastTag = 0;
    // private int offset, limit, lastTag = 0;
    private final int packedLimit = 0;
    private int fieldNumber = 1;



    public CpeInput(ByteBuffer buffer) {
        this.buffer = buffer;
    }

    /**
     * Returns the current offset (the position).
     */
    public int currentOffset() {
        return buffer.position();
    }

    /**
     * Returns the current limit (the end index).
     */
    public int currentLimit() {
        return buffer.limit();
    }

    /**
     * Return true if currently reading packed field
     */
    public boolean isCurrentFieldPacked() {
        return packedLimit != 0 && packedLimit != buffer.position();
    }

    /**
     * Returns the last tag.
     */
    public int getLastTag() {
        return lastTag;
    }

    /**
     * Attempt to read a field tag, returning zero if we have reached EOF. Protocol message parsers use this to read
     * tags, since a protocol message may legally end wherever a tag occurs, and zero is not a valid tag number.
     */
    public int readTag() throws IOException {
        return 0;
    }


    /**
     * Reads and discards a single field, given its tag value.
     *
     * @return {@code false} if the tag is an endgroup tag, in which case nothing is skipped. Otherwise, returns
     * {@code true}.
     */
    public boolean skipField(final int tag) throws IOException {
        return true;
    }

    /**
     * Reads and discards an entire message. This will read either until EOF or until an endgroup tag, whichever comes
     * first.
     */
    public void skipMessage() throws IOException {
        while (true) {
            final int tag = readTag();
            if (tag == 0 || !skipField(tag)) {
                return;
            }
        }
    }

    @Override
    public <T> void handleUnknownField(int fieldNumber, Schema<T> schema) throws IOException {
        skipField(lastTag);
    }

    @Override
    public <T> int readFieldNumber(Schema<T> schema) throws IOException {
        if (schema instanceof RuntimeSchema) {
            int count = ((RuntimeSchema<T>) schema).getFieldCount();
            if (fieldNumber > count) {
                lastTag = 0;
                return 0;
            }
        }

        if (!buffer.hasRemaining()) {
            lastTag = 0;
            return 0;
        }
        return fieldNumber++;

//        if (!buffer.hasRemaining())
//        {
//            lastTag = 0;
//            return 0;
//        }
//
//        // are we reading packed field?
//        if (isCurrentFieldPacked())
//        {
//            return lastTag >>> TAG_TYPE_BITS;
//        }
//
//        packedLimit = 0;
//        final int tag = readRawVarint32();
//        final int fieldNumber = tag >>> TAG_TYPE_BITS;
//        if (fieldNumber == 0)
//        {
//            if (decodeNestedMessageAsGroup &&
//                    WIRETYPE_TAIL_DELIMITER == (tag & TAG_TYPE_MASK))
//            {
//                // protostuff's tail delimiter for streaming
//                // 2 options: length-delimited or tail-delimited.
//                lastTag = 0;
//                return 0;
//            }
//            // If we actually read zero, that's not a valid tag.
//            throw ProtobufException.invalidTag();
//        }
//        if (decodeNestedMessageAsGroup && WIRETYPE_END_GROUP == (tag & TAG_TYPE_MASK))
//        {
//            lastTag = 0;
//            return 0;
//        }
//
//        lastTag = tag;
//        return fieldNumber;
    }


    /**
     * Read a {@code double} field value from the internal buffer.
     */
    @Override
    public double readDouble() throws IOException {
        return buffer.getDouble();
//        return Double.longBitsToDouble(readSFixed64());
    }

    /**
     * Read a {@code float} field value from the internal buffer.
     */
    @Override
    public float readFloat() throws IOException {
        return buffer.getFloat();
    }

    /**
     * Read a {@code uint64} field value from the internal buffer.
     */
    @Override
    public long readUInt64() throws IOException {
        return buffer.getLong();
    }

    /**
     * Read an {@code int64} field value from the internal buffer.
     */
    @Override
    public long readInt64() throws IOException {
        return buffer.getLong();
    }

    /**
     * Read an {@code int32} field value from the internal buffer.
     */
    @Override
    public int readInt32() throws IOException {
        return buffer.getInt();
    }

    /**
     * Read a {@code fixed64} field value from the internal buffer.
     */
    @Override
    public long readFixed64() throws IOException {
        return buffer.getLong();
    }

    /**
     * Read a {@code fixed32} field value from the internal buffer.
     */
    @Override
    public int readFixed32() throws IOException {
        return buffer.getInt();
    }

    /**
     * Read a {@code bool} field value from the internal buffer.
     */
    @Override
    public boolean readBool() throws IOException {
        return buffer.get() != 0;
    }

    /**
     * Read a {@code uint32} field value from the internal buffer.
     */
    @Override
    public int readUInt32() throws IOException {
        return buffer.getShort();
    }

    /**
     * Read an enum field value from the internal buffer. Caller is responsible for converting the numeric value to an
     * actual enum.
     */
    @Override
    public int readEnum() throws IOException {
        return buffer.getInt();
    }

    /**
     * Read an {@code sfixed32} field value from the internal buffer.
     */
    @Override
    public int readSFixed32() throws IOException {
        return buffer.getInt();
    }

    /**
     * Read an {@code sfixed64} field value from the internal buffer.
     */
    @Override
    public long readSFixed64() throws IOException {
        return readRawLittleEndian64();
    }

    /**
     * Read an {@code sint32} field value from the internal buffer.
     */
    @Override
    public int readSInt32() throws IOException {
        final int n = buffer.getInt();
        return (n >>> 1) ^ -(n & 1);
    }

    /**
     * Read an {@code sint64} field value from the internal buffer.
     */
    @Override
    public long readSInt64() throws IOException {
        final long n = buffer.getLong();
        return (n >>> 1) ^ -(n & 1);
    }

    @Override
    public String readString() throws IOException {
        throw new ProtostuffException("not support, string must specify length");
    }

    @Override
    public ByteString readBytes() throws IOException {
        return ByteString.wrap(readByteArray());
    }

    @Override
    public void readBytes(final ByteBuffer bb) throws IOException {
        bb.put(buffer);
    }

    @Override
    public byte[] readByteArray() throws IOException {
        throw new ProtostuffException("not support, byte array must specify length");
    }

    @Override
    public <T> T mergeObject(T value, final Schema<T> schema) throws IOException {
//        throw new ProtostuffException("not support Object.");


        if (value == null)
            value = schema.newMessage();
        CpeInput input = new CpeInput(buffer);
        schema.mergeFrom(input, value);
        if (!schema.isInitialized(value))
            throw new UninitializedMessageException(value, schema);
        return value;
    }

    @Override
    public <T> Collection<T> mergeObjectArray(T value, final Schema<T> schema, int length, int size) throws IOException {
//        throw new ProtostuffException("not support Object.");

        List<T> collect = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            T  item = schema.newMessage();
            byte[] buf = new byte[length];
            buffer.get(buf);
            ByteBuffer byteBuffer = ByteBuffer.wrap(buf);
            CpeInput input = new CpeInput(byteBuffer);
            schema.mergeFrom(input, item);
            if (!schema.isInitialized(item))
                throw new UninitializedMessageException(item, schema);
            collect.add(item);
        }
        return collect;
    }


    /**
     * Read a 64-bit little-endian integer from the internal byte buffer.
     */
    public long readRawLittleEndian64() throws IOException {
        // final byte[] buffer = this.buffer;
        // int offset = this.offset;

        final byte[] bs = new byte[8];
        buffer.get(bs);
        return (((long) bs[0] & 0xff)) |
                (((long) bs[1] & 0xff) << 8) |
                (((long) bs[2] & 0xff) << 16) |
                (((long) bs[3] & 0xff) << 24) |
                (((long) bs[4] & 0xff) << 32) |
                (((long) bs[5] & 0xff) << 40) |
                (((long) bs[6] & 0xff) << 48) |
                (((long) bs[7] & 0xff) << 56);
    }

    @Override
    public void transferByteRangeTo(Output output, boolean utf8String, int fieldNumber,
                                    boolean repeated) throws IOException {

    }

    @Override
    public String readString(int len) {
        byte[] tmp = new byte[len];
        buffer.get(tmp);
        return STRING.deser(tmp);
    }

    @Override
    public byte[] readByteArray(int len) {
        byte[] tmp = new byte[len];
        buffer.get(tmp);
        return tmp;
    }

    /**
     * Reads a byte array/ByteBuffer value.
     */
    @Override
    public ByteBuffer readByteBuffer() throws IOException {
        return ByteBuffer.wrap(readByteArray());
    }

    public ByteBuffer getBuffer() {
        return buffer;
    }
}
