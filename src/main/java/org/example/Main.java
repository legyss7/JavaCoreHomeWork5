package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class Main {

    private static final String sourceDir = "/home/ol/projects/JavaProjects/AndroidGB/JavaCore/homeWork5";
    private static final String backupDir = "/home/ol/projects/JavaProjects/AndroidGB/JavaCore/backup";

    public static void main(String[] args) throws IOException {
        backupDir(sourceDir, backupDir);
    }

    /**
     * Метод делает копию указанной директории, при повторном вызове
     * перезаписывает файлы, но папки остаются неизменными
     * (если из источника папка была удалена).
     * @param sourceDir  путь к исходной директории
     * @param backupDir путь к папке для резервного копирования
     * @throws IOException
     */
    public static void backupDir(String sourceDir, String backupDir) throws IOException {

        File backupDirFile = new File(backupDir);
        // создать директорию если ее нет
        if (!backupDirFile.exists()) {
            backupDirFile.mkdir();
        }

        File sourceDirFile = new File(sourceDir);
        for (File file : Objects.requireNonNull(sourceDirFile.listFiles())) {
            if (file.isFile()) {
                // если файл существует перезаписываем (REPLACE_EXISTING)
                Files.copy(file.toPath(), Paths.get(backupDir + "/" + file.getName()), REPLACE_EXISTING);
            }
            else {
                // если это директория, тогда рекурсия
                backupDir(file.getAbsolutePath(), backupDir + "/" + file.getName());
            }
        }
    }
}