package org.example;

import java.io.File;

public class Tree {

    public static void main(String[] args) {
        print(new File("/home/ol/projects/JavaProjects/AndroidGB/JavaCore/homeWork5/src"), "", "|", true);
    }

    /**
     * Метод печатает полноценное дерево директории
     * @param file путь к директории
     * @param indent отступы
     * @param indicator индикатор директорий
     * @param isLast флаг следующего элемента дерева
     */
    static void print(File file, String indent, String indicator, boolean isLast){
        System.out.print(indent);
        if (isLast){
            System.out.print("└─");
            indent += "  ";
        }
        else {
            System.out.print("├─");
            indent += "│ ";
        }
        System.out.println(indicator + file.getName() + indicator);

        File[] files = file.listFiles();
        if (files == null)
            return;

        int subDirTotal = 0;
        for (File element : files) {
            if (element.isDirectory())
                subDirTotal++;
        }

        int subFileTotal = 0;
        for (File item : files) {
            if (item.isFile())
                subFileTotal++;
        }

        int subDirCounter = 0;
        int subFileCounter = 0;
        for (File value : files) {
            if (value.isDirectory()) {
                print(value, indent, indicator,subDirTotal == ++subDirCounter);
            } else {
                print(value, indent, "", subFileTotal == ++subFileCounter);
            }
        }
    }

}
