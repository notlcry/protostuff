package io.protostuff.runtime;

import java.io.IOException;

import io.protostuff.*;

/**
 * Represents a field of a message/pojo.
 */
public abstract class Field<T>
{
    public final WireFormat.FieldType type;
    public final int number;
    public final String name;
    public final boolean repeated;
    public final int groupFilter;
    public final int fixedLength;

    // public final Tag tag;

    public Field(WireFormat.FieldType type, int number, String name, boolean repeated,
                 Tag tag, FixedLength fixedLength)
    {
        this.type = type;
        this.number = number;
        this.name = name;
        this.repeated = repeated;
        this.groupFilter = tag == null ? 0 : tag.groupFilter();
        // this.tag = tag;
        this.fixedLength = fixedLength == null ? 0: fixedLength.value();
    }

    public Field(WireFormat.FieldType type, int number, String name, boolean repeated,
                 Tag tag)
    {
        this.type = type;
        this.number = number;
        this.name = name;
        this.repeated = repeated;
        this.groupFilter = tag == null ? 0 : tag.groupFilter();
        // this.tag = tag;
        this.fixedLength = 0;
    }

    public Field(WireFormat.FieldType type, int number, String name, Tag tag, FixedLength fixedLength)
    {
        this(type, number, name, false, tag, fixedLength);
    }

    public Field(WireFormat.FieldType type, int number, String name, Tag tag) {
        this(type, number, name, false, tag, null);
    }

    /**
     * No copy by default.
     */
    protected Field<T> copy(IdStrategy strategy)
    {
        return this;
    }

    /**
     * Writes the value of a field to the {@code output}.
     */
    protected abstract void writeTo(Output output, T message)
            throws IOException;

    /**
     * Reads the field value into the {@code message}.
     */
    protected abstract void mergeFrom(Input input, T message)
            throws IOException;

    /**
     * Transfer the input field to the output field.
     */
    protected abstract void transfer(Pipe pipe, Input input, Output output,
            boolean repeated) throws IOException;
}
