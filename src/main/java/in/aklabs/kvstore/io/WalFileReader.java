package in.aklabs.kvstore.io;

import in.aklabs.kvstore.api.json.JsonUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class WalFileReader {
    List<String> walFilesOnDisk;

    public WalFileReader() {
        var walFiles = new File(".").list((dir, name) -> name.endsWith(".wal"));
        this.walFilesOnDisk = walFiles == null ? new LinkedList<>() : Arrays.asList(walFiles);
    }

    public Stream<WalRecord> read() {
        return walFilesOnDisk.stream()
                .map(fileName -> {
                    try {
                        return new BufferedReader(new FileReader(fileName));
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                })
                .flatMap(BufferedReader::lines)
                .map(line -> JsonUtils.fromJson(line, WalRecord.class));
    }

    public boolean deleteAllFilesOnDisk() {
        return walFilesOnDisk.stream().allMatch(file -> {
            return new File(file).delete();
        });
    }
}
