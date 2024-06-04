package se.thinkcode.version;

public class InMemoryVersionRepository implements VersionRepository {
    private Version version = null;

    @Override
    public void addVersion(Version version) {
        this.version = version;
    }

    @Override
    public Version getVersion() {
        return version;
    }
}