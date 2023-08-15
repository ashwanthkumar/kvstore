package in.aklabs.kvstore.io;

import in.aklabs.kvstore.api.json.JsonUtils;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.UUID;

public class WalFile {
    private final PrintWriter writer;

    public WalFile() throws FileNotFoundException {
        String walFile = UUID.randomUUID() + ".wal";
        this.writer = new PrintWriter(walFile);
    }

    public void record(WalRecord record) {
        writer.println(JsonUtils.toJson(record));
        writer.flush();
    }
}
