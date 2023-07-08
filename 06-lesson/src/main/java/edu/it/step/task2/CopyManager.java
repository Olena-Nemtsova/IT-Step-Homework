package edu.it.step.task2;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class CopyManager {
    private static final int MAX_UPLOAD = 5 * 1024;

    public void copy(String fromFilePath, String toDirPath) {
        String fileName = new File(fromFilePath).getName();
        String toFilePath = toDirPath + "\\copy-" + fileName;
        try {
            Files.createDirectories(Path.of(toDirPath));

            try (FileInputStream in = new FileInputStream(fromFilePath);
                 FileOutputStream out = new FileOutputStream(toFilePath)) {

                byte[] buffer = new byte[MAX_UPLOAD];
                int bytesRead;

                while ((bytesRead = in.read(buffer)) > 0) {
                    out.write(buffer, 0, bytesRead);
                    buffer = new byte[MAX_UPLOAD];
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void copyAll(String fromDirPath, String toDirPath) {
        File[] filesArr = new File(fromDirPath).listFiles();
        if (filesArr == null) {
            System.err.println("Files not found in path: " + fromDirPath);
            return;
        }
        Arrays.stream(filesArr).toList().forEach(file ->
                copy(file.getPath(), toDirPath)
        );
    }
}
