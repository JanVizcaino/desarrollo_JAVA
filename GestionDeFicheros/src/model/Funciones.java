package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Funciones {

    public static void createFolder(String folderName) {
        try {
            File folder = new File(folderName);
            if (!folder.exists()) {
                folder.mkdir();
            }
        } catch (Exception e) {
            // Error controlado sin imprimir nada
        }
    }

    public static void createFile(String path, String fileName, String content) {
        try {
            File file = new File(path, fileName);

            FileWriter writer = new FileWriter(file, true);
            writer.write(content);
            writer.close();

        } catch (IOException e) {
            // Manejo silencioso de errores
        }
    }

    public static String[] showListFiles(String path) {
        try {
            File folder = new File(path);

            if (folder.exists() && folder.isDirectory()) {
                return folder.list();
            } else {
                return new String[0]; // Ruta inválida o no es carpeta
            }

        } catch (Exception e) {
            return new String[0]; // Error al acceder
        }
    }

    public static String showFile(String path, String fileName) {
        StringBuilder content = new StringBuilder();

        try {
            File file = new File(path, fileName);
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
                reader.close();
            }
        } catch (IOException e) {
            // Retornará cadena vacía si hay error
        }

        return content.toString();
    }

    public static boolean overWriteFile(String path, String fileName, String newContent) {
        try {
            File file = new File(path, fileName);
            if (file.exists()) {
                FileWriter writer = new FileWriter(file, false); 
                writer.write(newContent);
                writer.close();
                return true;
            } else {
                return false; 
            }
        } catch (IOException e) {
            return false; 
        }
    }

    public static void deleteFile(String path, String fileName) {

    }

    public static int countChars(String path, String fileName) {
        return 0;
    }

    public static int countWords(String path, String fileName) {
        return 0;
    }

    public static String swapWords(String path, String fileName, String oldWord, String newContent) {
        return null;
    }

    public static void printPDF(String path, String fileName) {
    }
}
