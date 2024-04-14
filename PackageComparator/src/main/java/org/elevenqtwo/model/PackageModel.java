package org.elevenqtwo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PackageModel {
    private String name;
    private int epoch;
    private String version;
    private String release;
    private String arch;
    private String distTag;
    private int buildTime;
    private String source;
}
