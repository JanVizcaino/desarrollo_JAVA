package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

// import org.apache.pdfbox.pdmodel.PDDocument;
// import org.apache.pdfbox.pdmodel.PDPage;
// import org.apache.pdfbox.pdmodel.PDPageContentStream;
// import org.apache.pdfbox.pdmodel.font.PDType1Font;
/**
 * Clase que contiene funciones utilitarias para la gestión de archivos y
 * carpetas.
 */
public class Funciones {

    /**
     * Crea una carpeta en el directorio actual si no existe.
     *
     * @param folderName Nombre de la carpeta a crear.
     * @throws IOException Si no se puede crear la carpeta.
     */
    public static void createFolder(String folderName) throws IOException {
        String path = System.getProperty("user.dir");
        String separator = File.separator;

        String folderRoute = path + separator + folderName;

        File folder = new File(folderRoute);

        if (!folder.exists()) {
            if (!folder.mkdir()) {
                throw new IOException("No se pudo crear la carpeta: " + folderRoute);
            }
        }
    }

    /**
     * Crea un archivo en la ruta indicada y escribe contenido en él. Si ya
     * existe, añade el contenido.
     *
     * @param path Ruta relativa donde se creará el archivo.
     * @param fileName Nombre del archivo.
     * @param content Contenido a escribir.
     * @throws IOException Si ocurre un error al crear o escribir en el archivo.
     */
    public static void createFile(String path, String fileName, String content) throws IOException {
        String separator = File.separator;
        String userPath = System.getProperty("user.dir") + separator + path;

        String fileRoute = userPath + separator + fileName;

        File file = new File(fileRoute);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.write(content);
            bw.flush();
        }
    }

    /**
     * Devuelve una lista con los nombres de archivos contenidos en la carpeta
     * indicada.
     *
     * @param path Ruta relativa de la carpeta.
     * @return Array de nombres de archivos. Puede ser null si la ruta no es un
     * directorio válido.
     */
    public static String[] showListFiles(String path) {
        String separator = File.separator;
        String userPath = System.getProperty("user.dir") + separator + path;

        File folder = new File(userPath);
        return folder.list();
    }

    /**
     * Devuelve el contenido completo de un archivo como una cadena de texto.
     *
     * @param path Ruta relativa donde se encuentra el archivo.
     * @param fileName Nombre del archivo.
     * @return Contenido del archivo en forma de String.
     * @throws IOException Si ocurre un error al leer el archivo.
     */
    public static String showFile(String path, String fileName) throws IOException {
        String separator = File.separator;
        String userPath = System.getProperty("user.dir") + separator + path;

        String fileRoute = userPath + separator + fileName;

        FileReader fr = new FileReader(fileRoute);
        StringBuilder content;

        try (BufferedReader br = new BufferedReader(fr)) {
            String line;
            content = new StringBuilder();
            while ((line = br.readLine()) != null) {
                content.append(line);
            }
        }

        return content.toString();
    }

    /**
     * Sobrescribe completamente el contenido de un archivo con un nuevo
     * contenido.
     *
     * @param path Ruta relativa del archivo.
     * @param fileName Nombre del archivo.
     * @param newContent Nuevo contenido a escribir.
     * @return true si se sobrescribe correctamente.
     * @throws IOException Si ocurre un error al escribir en el archivo.
     */
    public static boolean overWriteFile(String path, String fileName, String newContent) throws IOException {
        String separator = File.separator;
        String userPath = System.getProperty("user.dir") + separator + path;

        String fileRoute = userPath + separator + fileName;

        File file = new File(fileRoute);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write(newContent);
            bw.flush();
        }

        return true;
    }

    /**
     * Elimina un archivo especificado en una ruta dada.
     *
     * @param path Ruta relativa donde se encuentra el archivo.
     * @param fileName Nombre del archivo a eliminar.
     * @return true si se eliminó con éxito, false si no.
     */
    public static boolean deleteFile(String path, String fileName) {
        String separator = File.separator;
        String userPath = System.getProperty("user.dir") + separator + path;

        String fileRoute = userPath + separator + fileName;
        return new File(fileRoute).delete();
    }

    /**
     * Cuenta el número total de caracteres en un archivo.
     *
     * @param path Ruta relativa del archivo.
     * @param fileName Nombre del archivo.
     * @return Número de caracteres del archivo.
     * @throws IOException Si ocurre un error al leer el archivo.
     */
    public static int countChars(String path, String fileName) throws IOException {
        String separator = File.separator;
        String userPath = System.getProperty("user.dir") + separator + path;

        String fileRoute = userPath + separator + fileName;

        File file = new File(fileRoute);

        int charCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            int c;
            while ((c = reader.read()) != -1) {
                charCount++;
            }
        }

        return charCount;
    }

    /**
     * Cuenta el número de palabras en un archivo.
     *
     * @param path Ruta relativa del archivo.
     * @param fileName Nombre del archivo.
     * @return Número de palabras en el archivo.
     * @throws IOException Si ocurre un error al leer el archivo.
     */
    public static int countWords(String path, String fileName) throws IOException {
        String separator = File.separator;
        String userPath = System.getProperty("user.dir") + separator + path;

        String fileRoute = userPath + separator + fileName;

        File file = new File(fileRoute);

        int wordCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.trim().split("\\s+");
                if (!line.trim().isEmpty()) {
                    wordCount += words.length;
                }
            }
        }

        return wordCount;
    }

    /**
     * Reemplaza todas las ocurrencias de una palabra por otra en el archivo
     * especificado.
     *
     * @param path Ruta relativa donde se encuentra el archivo.
     * @param fileName Nombre del archivo.
     * @param oldWord Palabra a reemplazar.
     * @param newWord Palabra nueva que reemplazará a la anterior.
     * @return Contenido actualizado del archivo.
     * @throws IOException Si ocurre un error al leer o escribir el archivo.
     */
    public static String swapWords(String path, String fileName, String oldWord, String newWord) throws IOException {
        String separator = File.separator;
        String userPath = System.getProperty("user.dir") + separator + path;

        String fileRoute = userPath + separator + fileName;

        File file = new File(fileRoute);

        StringBuilder content = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        }

        String updatedContent = content.toString().replace(oldWord, newWord);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write(updatedContent);
        }

        return updatedContent;
    }

// public static void printPDF(String path, String fileName) {
//     String separator = File.separator;
//     String userPath = System.getProperty("user.dir") + separator + path;
//     String fileRoute = userPath + separator + fileName;
//
//     File directory = new File(userPath);
//     try {
//         PDDocument document = new PDDocument();
//         PDPage page = new PDPage();
//         document.addPage(page);
//
//         PDPageContentStream contentStream = new PDPageContentStream(document, page);
//
//         contentStream.beginText();
//         contentStream.setFont(PDType1Font.HELVETICA, 18);
//         contentStream.newLineAtOffset(100, 700);
//         contentStream.showText("¡Hola desde PDFBox en Java! ??");
//         contentStream.endText();
//         contentStream.close();
//
//         document.save(fileRoute);
//         document.close();
//
//         System.out.println("PDF creado en: " + fileRoute);
//
//     } catch (Exception e) {
//         e.printStackTrace();
//     }
//
// }
}
