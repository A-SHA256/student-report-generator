package org.example.util;

import java.io.File;

public class CreateFileUtil {
    public String path;
    public File file;
    public void createFile() {
        path = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "report.csv";
        file = new File(path);

        if (file.getParentFile().mkdirs()) {
            System.out.println("File created");
        }
        System.out.println("File already exits");
    }
}
