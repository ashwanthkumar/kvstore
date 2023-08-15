package in.aklabs.kvstore.core;

import java.util.Optional;

public interface Store {
    void put(String key, String value);

    Optional<String> get(String key);

    boolean contains(String key);
}
