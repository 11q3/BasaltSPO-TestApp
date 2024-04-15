package org.elevenqtwo;

import org.elevenqtwo.service.PackageComparator;
import org.elevenqtwo.service.PackageFetcher;
import org.elevenqtwo.util.JsonWriter;

public class Main {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Usage: java " +
                    "-jar PackageComparator-1.0-SNAPSHOT.jar " +
                    "<branch1> <branch2> <outputFilePath>");
            return;
        }

        String branch1 = args[0];
        String branch2 = args[1];
        String filePath = args[2];

        PackageFetcher packageFetcher = new PackageFetcher();
        PackageComparator packageComparator = new PackageComparator();

        JsonWriter.writeJSONToFile(
                packageComparator.comparePackages(
                        packageFetcher.fetchPackages(branch1),
                        packageFetcher.fetchPackages(branch2)),
                        filePath);
    }
}