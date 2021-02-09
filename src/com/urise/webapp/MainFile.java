package com.urise.webapp;

import java.io.File;


public class MainFile {
    public static void main(String[] args) {
        //File dir = new File("C:\\Users\\Арина\\Desktop\\BaseJava\\basejava\\src\\com\\urise\\webapp");
        File dir = new File("./basejava/src/com/urise/webapp/");
        System.out.println(dir.isDirectory());
        for (String name : dir.list()) {
            System.out.println(name);
        }
        runAll(dir);
    }

    public static void runAll(File dir) {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    System.out.println("\nDirectory " + f.getName() + " files :");
                    runAll(f);
                    System.out.println("");
                } else if (f.isFile())
                    System.out.println("  " + f.getName());
            }
        }
    }
}


