package in.aklabs.kvstore.api;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonTransformerTest {
    @Test
    public void testJsonTransformForOptional() {
        assertEquals("null", new JsonTransformer().render(Optional.empty()));
    }

}