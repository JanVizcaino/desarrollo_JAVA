package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class Funciones {

    public static void createFolder(String folderName) {
        try {
            String path = System.getProperty("user.dir");
            String separator = File.separator;

            String folderRoute = path + separator + folderName;

            File folder = new File(folderRoute);

            if (!folder.exists()) {
                folder.mkdir();
            }
        } catch (Exception e) {
        }
    }

    public static void createFile(String path, String fileName, String content) {

        String separator = File.separator;
        String userPath = System.getProperty("user.dir") + separator + path;

        String fileRoute = userPath + separator + fileName;

        File file = new File(fileRoute);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.write(content);
            bw.flush();
        } catch (IOException e) {
            // Manejo silencioso de errores
        }
    }

    public static String[] showListFiles(String path) {
        String separator = File.separator;
        String userPath = System.getProperty("user.dir") + separator + path;

        File folder = new File(userPath);
        try {
            String[] documentList = folder.list();
            return documentList;
        } catch (Exception e) {
            return new String[0];
        }
    }

    public static String showFile(String path, String fileName) {

        String separator = File.separator;
        String userPath = System.getProperty("user.dir") + separator + path;

        String fileRoute = userPath + separator + fileName;

        try {
            FileReader fr = new FileReader(fileRoute);

            BufferedReader br = new BufferedReader(fr);

            String line;
            String content = "";

            while ((line = br.readLine()) != null) {
                content += line;
            }

            return content;

        } catch (IOException e) {
            return "Error" + e;
        }

    }

    public static boolean overWriteFile(String path, String fileName, String newContent) {

        String separator = File.separator;
        String userPath = System.getProperty("user.dir") + separator + path;

        String fileRoute = userPath + separator + fileName;

        File file = new File(fileRoute);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write(newContent);
            bw.flush();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static void deleteFile(String path, String fileName) {
        String separator = File.separator;
        String userPath = System.getProperty("user.dir") + separator + path;

        String fileRoute = userPath + separator + fileName;

        File file = new File(fileRoute);

        boolean sucess = true;

        if (!file.delete()) {
            sucess = false;
        }

        System.out.println(fileRoute);
        System.out.println(sucess);

    }

    public static int countChars(String path, String fileName) {
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
            return charCount;

        } catch (IOException e) {
            return 0;
        }
    }

    public static int countWords(String path, String fileName) {
        String separator = File.separator;
        String userPath = System.getProperty("user.dir") + separator + path;

        String fileRoute = userPath + separator + fileName;

        File file = new File(fileRoute);

        int wordCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.trim().split("\\s+"); // Separa por espacios, tabulaciones, etc.
                if (!line.trim().isEmpty()) {
                    wordCount += words.length;
                }
            }

            return wordCount;
        } catch (IOException e) {
            return 0;
        }
    }

    public static String swapWords(String path, String fileName, String oldWord, String newWord) {
        String separator = File.separator;
        String userPath = System.getProperty("user.dir") + separator + path;

        String fileRoute = userPath + separator + fileName;

        File file = new File(fileRoute);

        try {
            String content = "";
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    content += line + System.lineSeparator();
                }
            }

            String updatedContent = content.replace(oldWord, newWord);

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                bw.write(updatedContent);
            }

            return updatedContent;
        } catch (IOException e) {
            return null;
        }
    }

    public static void printPDF(String path, String fileName) {
        String separator = File.separator;
        String userPath = System.getProperty("user.dir") + separator + path;
        String fileRoute = userPath + separator + fileName;

        File directory = new File(userPath);
        try {
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 18);
            contentStream.newLineAtOffset(100, 700);
            contentStream.showText("¡Hola desde PDFBox en Java! ??");
            contentStream.endText();
            contentStream.close();

            document.save(fileRoute);
            document.close();

            System.out.println("PDF creado en: " + fileRoute);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
