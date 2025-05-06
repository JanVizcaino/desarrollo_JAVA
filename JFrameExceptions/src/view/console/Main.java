package view.console;

import model.exceptions.InvalidIdException;
import model.exceptions.InvalidDateException;
import com.sun.security.jgss.GSSUtil;
import model.validations.UserDataValidations;

import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        do {
            System.out.print("\nWelcome to the ID function tester menu.\n" +
                    "   1.-testerCheckId()\n" +
                    "   2.-testerFormatDate()\n" +
                    "   3.-testerCalCulateAge()\n" +
                    "   4.-testerCheckPostalCode()\n" +
                    "   5.-testerIsNumeric()\n" +
                    "   6.-testerIsAlphabetic()\n" +
                    "   7.-testerCheckEmail()\n" +
                    "   8.-testerCheckName()\n" +
                    "Please, select an option [1-8]: ");
            int option = sc.nextInt();

            switch (option){
                case 1:
                    testerCheckId();
                    break;
                case 2:
                    testerFormatDate();
                    break;
                case 3:
                      testerCalculateAge();
                      break;
                case 4:
                      testerCheckPostalCode();
                      break;
                case 5:
                      testerIsNumeric();
                      break;
                case 6:
                      testerIsAlphabetic();
                      break;
                case 7:
                       testerCheckEmail();
                       break;
                case 8:
                       testerCheckName();
                       break;
                case 0:
                        break;
                default:
                        System.out.println("Invalid option");
                        break;
            }
        }while(true);
    }

private static void testerCheckId() {
    System.out.print("Enter your id: ");
    String id = sc.next();
    try {
        UserDataValidations.checkId(1, id);
        System.out.println(UserDataValidations.colorGreen("Correct ID"));
        
    } catch (InvalidIdException e) {
        System.out.println(UserDataValidations.colorRed(e.getMessage()));
    }
}

private static void testerFormatDate() {
    System.out.print("Enter a date (dd/MM/yyyy): ");
    String date = sc.next();
    try {
        UserDataValidations.checkFormatDate(date); 
        System.out.println(UserDataValidations.colorGreen("Correct date format"));
    } catch (InvalidDateException e) {
        System.out.println(UserDataValidations.colorRed(e.getMessage()));
    }
}

private static void testerCalculateAge() {
    System.out.print("Enter your birth date (dd/MM/yyyy): ");
    String birthDate = sc.next();
    try {
        int age = UserDataValidations.calculateAge(birthDate);
        System.out.println(UserDataValidations.colorGreen("You are " + age + " years old."));
    } catch (InvalidDateException e) {
        System.out.println(UserDataValidations.colorRed(e.getMessage()));
    }
}


    private static void testerCheckPostalCode() {
        System.out.print("Enter your postal code (nnnnn): ");
        String postalCode = sc.next();
        if (UserDataValidations.checkPostalCode(postalCode)){
            System.out.println(UserDataValidations.colorGreen("Correct postal code format"));
        }else{
            System.out.println(UserDataValidations.colorRed("Incorrect postal code format"));
        }
    }

    private static void testerIsNumeric() {
        System.out.print("Enter a number: ");
        String number = sc.next();
        if (UserDataValidations.isNumeric(number)){
            System.out.println(UserDataValidations.colorGreen("It's a number"));
        }else{
            System.out.println(UserDataValidations.colorRed("It's not numeric"));
        }
    }

    private static void testerIsAlphabetic() {
        System.out.print("Enter a alphabetic string: ");
        String str = sc.next();
        if (UserDataValidations.isAlphabetic(str)){
            System.out.println(UserDataValidations.colorGreen("It's a alphabetic"));
        }else{
            System.out.println(UserDataValidations.colorRed("It's not alphabetic"));
        }
    }

    private static void testerCheckEmail() {
        System.out.print("Enter a valid email address: ");
        String email = sc.next();
        if (UserDataValidations.checkEmail(email)){
            System.out.println(UserDataValidations.colorGreen("Correct email"));
        }else{
            System.out.println(UserDataValidations.colorRed("Incorrect email format"));
        }
    }

    private static void testerCheckName() {
        System.out.print("Enter a name: ");
        String name = sc.next();
        if (UserDataValidations.checkName(name)){
            System.out.println(UserDataValidations.colorGreen("Correct name"));
        }
        else{
            System.out.println(UserDataValidations.colorRed("Incorrect name"));
        }
    }
}
