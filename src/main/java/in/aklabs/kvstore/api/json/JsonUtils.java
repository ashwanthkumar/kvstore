package in.aklabs.kvstore.api.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Optional;

public class JsonUtils {
    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(Optional.empty().getClass(), new OptionalTypeAdapter())
            .create();

    public static String toJson(Object input) {
        return gson.toJson(input);
    }

    public static <T> T fromJson(String json, Class<T> typeClazz) {
        return gson.fromJson(json, typeClazz);
    }
}
