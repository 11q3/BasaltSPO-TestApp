package org.elevenqtwo;

public class PackageComparator {
    public static void main(String[] args) {
        PackageFetcher packageFetcher = new PackageFetcher();
        var a = packageFetcher.fetchPackages("p9", "x86_64-i586");

        System.out.println(a.toString() + "a");
    }
}