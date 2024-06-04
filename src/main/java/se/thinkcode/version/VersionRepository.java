package se.thinkcode.version;

public interface VersionRepository {
    void addVersion(Version version);

    Version getVersion();
}