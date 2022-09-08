package gorest.test.api.extension;

import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.nio.charset.Charset;

public class JsonStringEntity extends StringEntity {
    public JsonStringEntity(String string, Charset charset) {
        super(string, ContentType.APPLICATION_JSON.withCharset(charset));
    }

    public JsonStringEntity(String string, Charset charset, boolean chunked) {
        super(string, ContentType.APPLICATION_JSON.withCharset(charset), chunked);
    }

    public JsonStringEntity(String string) {
        super(string, ContentType.APPLICATION_JSON);
    }
}
