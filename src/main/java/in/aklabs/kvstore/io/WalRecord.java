package in.aklabs.kvstore.io;

public record WalRecord(WalAction action, String key, String value) {
}
