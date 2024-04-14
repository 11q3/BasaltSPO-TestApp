package org.elevenqtwo.model;

public class PackageModel {
    public PackageModel(String name, int epoch, String version, String release, String arch, String distTag, int buildTime, String source) {
        this.name = name;
        this.epoch = epoch;
        this.version = version;
        this.release = release;
        this.arch = arch;
        this.distTag = distTag;
        this.buildTime = buildTime;
        this.source = source;
    }

    private final String name;
    private final int epoch;
    private final String version;
    private final String release;
    private final String arch;
    private final String distTag;
    private final int buildTime;
    private final String source;

    public String getName() {
        return name;
    }

    public int getEpoch() {
        return epoch;
    }

    public String getVersion() {
        return version;
    }

    public String getRelease() {
        return release;
    }

    public String getArch() {
        return arch;
    }

    public String getDistTag() {
        return distTag;
    }

    public int getBuildTime() {
        return buildTime;
    }

    public String getSource() {
        return source;
    }
}
