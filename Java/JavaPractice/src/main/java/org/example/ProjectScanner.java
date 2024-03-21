package org.example;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ProjectScanner {
    public static List<File> findJavaFiles(String directoryPath) {
        List<File> javaFiles = new ArrayList<>();
        File directory = new File(directoryPath);

        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file: files) {
                    if (file.isFile() && file.getName().endsWith(".java")) {
                        javaFiles.add(file);
                    } else if (file.isDirectory()) {
                        javaFiles.addAll(findJavaFiles(file.getPath()));
                    }
                }
            }
        }
        return javaFiles;
    }
}
