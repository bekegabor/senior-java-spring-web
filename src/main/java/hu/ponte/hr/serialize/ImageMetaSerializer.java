package hu.ponte.hr.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import hu.ponte.hr.entity.ImageMeta;

import java.io.IOException;

public class ImageMetaSerializer extends StdSerializer<ImageMeta> {

    public ImageMetaSerializer() {
        this(null);
    }

    public ImageMetaSerializer(Class<ImageMeta> t) {
        super(t);
    }

    @Override
    public void serialize(ImageMeta value, JsonGenerator jgen, SerializerProvider provider)throws IOException {

        jgen.writeStartObject();
        jgen.writeStringField("id", value.getId().toString());
        jgen.writeStringField("name", value.getName());
        jgen.writeStringField("mimeType", value.getMimeType());
        jgen.writeNumberField("size", value.getSize());
        jgen.writeStringField("digitalSign", value.getDigitalSign());
        jgen.writeEndObject();
    }
}
