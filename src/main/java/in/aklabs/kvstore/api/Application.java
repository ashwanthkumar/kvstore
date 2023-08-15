package in.aklabs.kvstore.api;

import in.aklabs.kvstore.core.InmemoryStore;
import in.aklabs.kvstore.core.Store;

import java.io.FileNotFoundException;

import static spark.Spark.get;
import static spark.Spark.put;

public class Application {
    public static void main(String[] args) throws FileNotFoundException {
        Store store = new InmemoryStore();

        get("/get/:key", (req, res) -> {
            res.type("application/json");
            var key = req.params("key");
            return store.get(key);
        }, new JsonTransformer());

        put("/put/:key", (req, res) -> {
            res.type("application/json");
            var key = req.params("key");
            var value = req.body();
            store.put(key, value);
            return "OK";
        }, new JsonTransformer());
    }
}
