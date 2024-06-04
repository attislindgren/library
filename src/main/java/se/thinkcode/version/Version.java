package se.thinkcode.version;

import java.util.Objects;

public final class Version {
    private final String version;

    public Version(String version) {
        this.version = version;
    }

    public String version() {
        return version;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Version) obj;
        return Objects.equals(this.version, that.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(version);
    }

    @Override
    public String toString() {
        return "Version[" +
                "version=" + version + ']';
    }
}
