package org.elevenqtwo.model;

public class PackageModel {
    public PackageModel(String name, String version) {
        this.name = name;
        this.version = version;
    }

    private String name;

    private String version;
    public String getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "{ \"name:\"" + name + "," +
                   "version:" + version +
                "}\n"; //TODO remove \n
    }
}
