package com.ssafy.common.util;

import java.io.File;

public class FileUtil {
    static FileUtil instance;
    private FileUtil() {}

    public static FileUtil getInstance() {
        if (instance == null) {
            instance = new FileUtil();
        }
        return instance;
    }
    public File createFilePath(String path) {
        File file = new File(path);
        file = new File(file.getAbsoluteFile().toString());
        if (!file.exists()) {
            file.mkdir();
        }
        return file;
    }
}
