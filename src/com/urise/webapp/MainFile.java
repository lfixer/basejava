package com.urise.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainFile {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\Арина\\Desktop\\BaseJava\\basejava\\.gitignore";
        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }
        File dir = new File("C:\\Users\\Арина\\Desktop\\BaseJava\\basejava\\src\\com\\urise\\webapp");
        System.out.println(dir.isDirectory());
        for (String name : dir.list()) {
            System.out.println(name);
        }

        try (FileInputStream fis = new FileInputStream(filePath)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        runAll(file);
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


