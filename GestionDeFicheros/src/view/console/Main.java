package view.console;

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
            System.out.println("10. Imprimir PDF");
            System.out.println("0. Salir");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (choice) {
                case 1:
                    System.out.print("Ingrese el nombre de la carpeta: ");
                    String folderName = scanner.nextLine();
                    Funciones.createFolder(folderName);
                    break;
                case 2:
                    System.out.print("Ingrese la ruta del archivo: ");
                    String filePath = scanner.nextLine();
                    System.out.print("Ingrese el nombre del archivo: ");
                    String fileName = scanner.nextLine();
                    System.out.print("Ingrese el contenido del archivo: ");
                    String content = scanner.nextLine();
                    Funciones.createFile(filePath, fileName, content);
                    break;
                case 3:
                    System.out.print("Ingrese la ruta para ver la lista de archivos: ");
                    String path = scanner.nextLine();
                    String[] files = Funciones.showListFiles(path);
                    if (files != null) {
                        for (String file : files) {
                            System.out.println(file);
                        }
                    } else {
                        System.out.println("No se encontraron archivos.");
                    }
                    break;
                case 4:
                    System.out.print("Ingrese la ruta del archivo: ");
                    String viewPath = scanner.nextLine();
                    System.out.print("Ingrese el nombre del archivo a ver: ");
                    String viewFile = scanner.nextLine();
                    String fileContent = Funciones.showFile(viewPath, viewFile);
                    if (fileContent != null) {
                        System.out.println(fileContent);
                    } else {
                        System.out.println("El archivo no existe.");
                    }
                    break;
                case 5:
                    System.out.print("Ingrese la ruta del archivo: ");
                    String overwritePath = scanner.nextLine();
                    System.out.print("Ingrese el nombre del archivo: ");
                    String overwriteFile = scanner.nextLine();
                    System.out.print("Ingrese el nuevo contenido: ");
                    String newContent = scanner.nextLine();
                    boolean success = Funciones.overWriteFile(overwritePath, overwriteFile, newContent);
                    if (success) {
                        System.out.println("Archivo sobrescrito correctamente.");
                    } else {
                        System.out.println("Hubo un error al sobrescribir el archivo.");
                    }
                    break;
                case 6:
                    System.out.print("Ingrese la ruta del archivo: ");
                    String deletePath = scanner.nextLine();
                    System.out.print("Ingrese el nombre del archivo: ");
                    String deleteFile = scanner.nextLine();
                    Funciones.deleteFile(deletePath, deleteFile);
                    break;
                case 7:
                    System.out.print("Ingrese la ruta del archivo: ");
                    String countCharsPath = scanner.nextLine();
                    System.out.print("Ingrese el nombre del archivo: ");
                    String countCharsFile = scanner.nextLine();
                    int charCount = Funciones.countChars(countCharsPath, countCharsFile);
                    System.out.println("El archivo contiene " + charCount + " caracteres.");
                    break;
                case 8:
                    System.out.print("Ingrese la ruta del archivo: ");
                    String countWordsPath = scanner.nextLine();
                    System.out.print("Ingrese el nombre del archivo: ");
                    String countWordsFile = scanner.nextLine();
                    int wordCount = Funciones.countWords(countWordsPath, countWordsFile);
                    System.out.println("El archivo contiene " + wordCount + " palabras.");
                    break;
                case 9:
                    System.out.print("Ingrese la ruta del archivo: ");
                    String swapWordsPath = scanner.nextLine();
                    System.out.print("Ingrese el nombre del archivo: ");
                    String swapWordsFile = scanner.nextLine();
                    System.out.print("Ingrese la palabra a reemplazar: ");
                    String oldWord = scanner.nextLine();
                    System.out.print("Ingrese la nueva palabra: ");
                    String newWord = scanner.nextLine();
                    String result = Funciones.swapWords(swapWordsPath, swapWordsFile, oldWord, newWord);
                    if (result != null) {
                        System.out.println("Contenido actualizado: \n" + result);
                    } else {
                        System.out.println("Hubo un error al cambiar las palabras.");
                    }
                    break;
                case 10:
                    System.out.print("Ingrese la ruta del archivo: ");
                    String pdfPath = scanner.nextLine();
                    System.out.print("Ingrese el nombre del archivo: ");
                    String pdfFile = scanner.nextLine();
                    Funciones.printPDF(pdfPath, pdfFile);
                    System.out.println("PDF impreso.");
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida, intente nuevamente.");
            }
        }
    }
}
