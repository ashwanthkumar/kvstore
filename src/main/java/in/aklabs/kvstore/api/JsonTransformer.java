package in.aklabs.kvstore.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import in.aklabs.kvstore.api.json.JsonUtils;
import in.aklabs.kvstore.api.json.OptionalTypeAdapter;
import spark.ResponseTransformer;

import java.util.Optional;

public class JsonTransformer implements ResponseTransformer {


    @Override
    public String render(Object model) {
        return JsonUtils.toJson(model);
    }

}