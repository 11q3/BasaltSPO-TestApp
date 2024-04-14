package org.elevenqtwo;

import org.elevenqtwo.service.PackageComparator;
import org.elevenqtwo.service.PackageFetcher;

public class Main {
    public static void main(String[] args) {
        PackageFetcher packageFetcher = new PackageFetcher();
        PackageComparator packageComparator = new PackageComparator();

        System.out.println(1);

        var a = packageFetcher.fetchPackages("p9", "x86_64-i586");
        System.out.println(2);
        var b = packageFetcher.fetchPackages("sisyphus", "x86_64-i586");
        System.out.println(3);

        System.out.println(packageComparator.comparePackages(a, b));
    }
}