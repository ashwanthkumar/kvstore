package in.aklabs.kvstore.core;

import in.aklabs.kvstore.io.WalAction;
import in.aklabs.kvstore.io.WalFile;
import in.aklabs.kvstore.io.WalFileReader;
import in.aklabs.kvstore.io.WalRecord;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InmemoryStore implements Store {
    WalFile walFile;
    Map<String, String> underlying = new ConcurrentHashMap<>();

    public InmemoryStore() throws FileNotFoundException {
        var walReader = new WalFileReader();
        walReader.read()
                .forEach(record -> {
                    if (record.action() == WalAction.PUT) {
                        underlying.put(record.key(), record.value());
                    }
                });
//        walReader.deleteAllFilesOnDisk();

        this.walFile = new WalFile();
    }

    @Override
    public void put(String key, String value) {
        walFile.record(new WalRecord(WalAction.PUT, key, value));
        underlying.put(key, value);
    }

    @Override
    public Optional<String> get(String key) {
        return Optional.ofNullable(underlying.get(key));
    }

    @Override
    public boolean contains(String key) {
        return underlying.containsKey(key);
    }
}
