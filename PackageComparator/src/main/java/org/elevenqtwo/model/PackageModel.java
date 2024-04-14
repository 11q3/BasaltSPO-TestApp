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

    private String name;
    private int epoch;
    private String version;
    private String release;
    private String arch;
    private String distTag;
    private int buildTime;
    private String source;

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
