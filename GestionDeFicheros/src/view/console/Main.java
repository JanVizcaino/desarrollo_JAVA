package view.console;

import java.io.IOException;
import model.Funciones;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Seleccione una opcion:");
            System.out.println("1. Crear carpeta");
            System.out.println("2. Crear archivo");
            System.out.println("3. Ver lista de archivos");
            System.out.println("4. Ver archivo");
            System.out.println("5. Sobrescribir archivo");
            System.out.println("6. Eliminar archivo");
            System.out.println("7. Contar caracteres en archivo");
            System.out.println("8. Contar palabras en archivo");
            System.out.println("9. Cambiar palabras en archivo");
            //System.out.println("10. Imprimir PDF");
            System.out.println("0. Salir");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (choice) {
                case 1 -> {
                    System.out.print("Ingrese el nombre de la carpeta: ");
                    String folderName = scanner.nextLine();
                    try {
                        Funciones.createFolder(folderName);
                        System.out.println("Carpeta creada correctamente.");
                    } catch (IOException e) {
                        System.out.println("Error al crear la carpeta: " + e.getMessage());
                    }
                }
                case 2 -> {
                    System.out.print("Ingrese la ruta del archivo: ");
                    String filePath = scanner.nextLine();
                    System.out.print("Ingrese el nombre del archivo: ");
                    String fileName = scanner.nextLine();
                    System.out.print("Ingrese el contenido del archivo: ");
                    String content = scanner.nextLine();
                    try {
                        Funciones.createFile(filePath, fileName, content);
                        System.out.println("Archivo creado correctamente.");
                    } catch (IOException e) {
                        System.out.println("Error al crear el archivo: " + e.getMessage());
                    }
                }
                case 3 -> {
                    System.out.print("Ingrese la ruta para ver la lista de archivos: ");
                    String path = scanner.nextLine();
                    String[] files = Funciones.showListFiles(path);
                    if (files != null && files.length > 0) {
                        System.out.println("Archivos encontrados:");
                        for (String file : files) {
                            System.out.println("- " + file);
                        }
                    } else {
                        System.out.println("No se encontraron archivos.");
                    }
                }
                case 4 -> {
                    System.out.print("Ingrese la ruta del archivo: ");
                    String viewPath = scanner.nextLine();
                    System.out.print("Ingrese el nombre del archivo a ver: ");
                    String viewFile = scanner.nextLine();
                    try {
                        String fileContent = Funciones.showFile(viewPath, viewFile);
                        System.out.println("Contenido del archivo:\n" + fileContent);
                    } catch (IOException e) {
                        System.out.println("Error al leer el archivo: " + e.getMessage());
                    }
                }
                case 5 -> {
                    System.out.print("Ingrese la ruta del archivo: ");
                    String overwritePath = scanner.nextLine();
                    System.out.print("Ingrese el nombre del archivo: ");
                    String overwriteFile = scanner.nextLine();
                    System.out.print("Ingrese el nuevo contenido: ");
                    String newContent = scanner.nextLine();
                    try {
                        boolean success = Funciones.overWriteFile(overwritePath, overwriteFile, newContent);
                        if (success) {
                            System.out.println("Archivo sobrescrito correctamente.");
                            // Muestra el contenido actualizado
                            String updatedContent = Funciones.showFile(overwritePath, overwriteFile);
                            System.out.println("Nuevo contenido:\n" + updatedContent);
                        }
                    } catch (IOException e) {
                        System.out.println("Error al sobrescribir el archivo: " + e.getMessage());
                    }
                }
                case 6 -> {
                    System.out.print("Ingrese la ruta del archivo: ");
                    String deletePath = scanner.nextLine();
                    System.out.print("Ingrese el nombre del archivo: ");
                    String deleteFile = scanner.nextLine();

                    boolean deleted = Funciones.deleteFile(deletePath, deleteFile);
                    if (deleted) {
                        System.out.println("Archivo eliminado correctamente.");
                    } else {
                        System.out.println("No se pudo eliminar el archivo o no existe.");
                    }
                }
                case 7 -> {
                    System.out.print("Ingrese la ruta del archivo: ");
                    String countCharsPath = scanner.nextLine();
                    System.out.print("Ingrese el nombre del archivo: ");
                    String countCharsFile = scanner.nextLine();
                    try {
                        int charCount = Funciones.countChars(countCharsPath, countCharsFile);
                        System.out.println("El archivo contiene " + charCount + " caracteres.");
                    } catch (IOException e) {
                        System.out.println("Error al contar caracteres: " + e.getMessage());
                    }
                }
                case 8 -> {
                    System.out.print("Ingrese la ruta del archivo: ");
                    String countWordsPath = scanner.nextLine();
                    System.out.print("Ingrese el nombre del archivo: ");
                    String countWordsFile = scanner.nextLine();
                    try {
                        int wordCount = Funciones.countWords(countWordsPath, countWordsFile);
                        System.out.println("El archivo contiene " + wordCount + " palabras.");
                    } catch (IOException e) {
                        System.out.println("Error al contar palabras: " + e.getMessage());
                    }
                }
                case 9 -> {
                    System.out.print("Ingrese la ruta del archivo: ");
                    String swapWordsPath = scanner.nextLine();
                    System.out.print("Ingrese el nombre del archivo: ");
                    String swapWordsFile = scanner.nextLine();
                    System.out.print("Ingrese la palabra a reemplazar: ");
                    String oldWord = scanner.nextLine();
                    System.out.print("Ingrese la nueva palabra: ");
                    String newWord = scanner.nextLine();
                    try {
                        String result = Funciones.swapWords(swapWordsPath, swapWordsFile, oldWord, newWord);
                        System.out.println("Contenido actualizado: \n" + result);
                    } catch (IOException e) {
                        System.out.println("Error al reemplazar palabras: " + e.getMessage());
                    }
                }
                case 0 -> {
                    System.out.println("Saliendo...");
                    scanner.close();
                    return;
                }
                default ->
                    System.out.println("Opción no válida, intente nuevamente.");
            }

            //case 10:
            //    System.out.print("Ingrese la ruta del archivo: ");
            //    String pdfPath = scanner.nextLine();
            //    System.out.print("Ingrese el nombre del archivo: ");
            //    String pdfFile = scanner.nextLine();
            //    Funciones.printPDF(pdfPath, pdfFile);
            //    System.out.println("PDF impreso.");
            //    break;
        }
    }
}
