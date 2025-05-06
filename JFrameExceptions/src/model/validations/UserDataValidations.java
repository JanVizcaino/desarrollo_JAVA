package model.validations;

import java.security.KeyStore;
import java.time.LocalDateTime;
import java.util.Arrays;

import model.exceptions.InvalidIdException;
import model.exceptions.InvalidDateException;

public class UserDataValidations {

    /**
     * This function validates if an ID has a correct format
     *
     * @param typeDoc Compared type of document
     * @param id Compared number of id
     * @throws model.exceptions.InvalidIdException
     */
    public static void checkId(int typeDoc, String id) throws InvalidIdException {
        String[] abc = {"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"};
        if (typeDoc == 1 && id.length() == 9) {
            String idC = id.substring(0, id.length() - 1);
            if (isNumeric(idC)) {
                int idInt = Integer.parseInt(idC);
                if (id.charAt(id.length() - 1) == abc[idInt % 23].charAt(0)) {
                    return;
                }
            }
        }
        throw new InvalidIdException("Formato de ID incorrecto.");
    }

    /**
     * @param date Compared date
     * @throws model.exceptions.InvalidDateException
     */
    public static void checkFormatDate(String date) throws InvalidDateException {
        if (!isAlphabetic(date) && !isNumeric(date)) {
            String[] dates = date.split("/");
            if (dates.length != 3) {
                throw new InvalidDateException("Fecha incompleta.");
            }
            int day = Integer.parseInt(dates[0]);
            int month = Integer.parseInt(dates[1]);
            int year = Integer.parseInt(dates[2]);

            if (year > LocalDateTime.now().getYear() || year < 1930) {
                throw new InvalidDateException("Año fuera de rango válido.");
            }
            if (month < 1 || month > 12) {
                throw new InvalidDateException("Mes fuera de rango válido.");
            }

            switch (month) {
                case 4, 6, 9, 11 -> {
                    if (day < 1 || day > 30) {
                        throw new InvalidDateException("Día inválido.");
                    }
                }
                case 2 -> {
                    boolean isLeap = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
                    if (day < 1 || day > (isLeap ? 29 : 28)) {
                        throw new InvalidDateException("Día inválido en febrero.");
                    }
                }
                default -> {
                    if (day < 1 || day > 31) {
                        throw new InvalidDateException("Día inválido.");
                    }
                }
            }
            return;
        }
        throw new InvalidDateException("Formato de fecha inválido.");
    }

    /**
     * @param birthDate date of birth
     * @return age
     * @throws model.exceptions.InvalidDateException
     */
    public static int calculateAge(String birthDate) throws InvalidDateException {
        checkFormatDate(birthDate); // lanza excepción si no es válida

        String[] dates = birthDate.split("/");
        int birthDay = Integer.parseInt(dates[0]);
        int birthMonth = Integer.parseInt(dates[1]);
        int birthYear = Integer.parseInt(dates[2]);

        int age = LocalDateTime.now().getYear() - birthYear;

        if (birthMonth < LocalDateTime.now().getMonthValue()) {
            return age;
        } else if (birthMonth > LocalDateTime.now().getMonthValue()) {
            return age - 1;
        } else {
            return (birthDay <= LocalDateTime.now().getDayOfMonth()) ? age : age - 1;
        }
    }

    /**
     * @param zip postal code
     * @return valid format
     */
    public static boolean checkPostalCode(String zip) {
        if (zip.length() == 5) {
            for (int i = 0; i < 5; i++) {
                if (!Character.isDigit(zip.charAt(i))) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param str string
     * @return if its numeric
     */
    public static boolean isNumeric(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    /**
     * @param str string
     * @return if its alphabetic
     */
    public static boolean isAlphabetic(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isLetter(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param email user's
     * @return if its format is correct
     */
    public static boolean checkEmail(String email) {
        String[] email_endings
                = {".com", ".org", ".net", ".edu", ".gov", ".mil", ".int",
                    ".co", ".io", ".biz", ".info", ".tv", ".me", ".co.uk",
                    ".us", ".ca", ".de", ".fr", ".es", ".it", ".au", ".jp",
                    ".cn", ".in", ".ru", ".br", ".za", ".mx", ".se", ".nl",
                    ".no", ".fi", ".dk", ".pl", ".ch", ".at", ".be", ".cz",
                    ".hu", ".kr", ".sg", ".il", ".sa", ".ae", ".hk", ".tw"};

        if (email.contains("@")) {
            String[] emailContent = email.split("@");
            if (emailContent.length == 2) {
                String[] domainParts = emailContent[1].split("\\.");
                String emailEnding = "." + domainParts[domainParts.length - 1];
                return Arrays.asList(email_endings).contains(emailEnding);
            }
        } else {
            return false;
        }
        return false;
    }

    /**
     *
     * @param name user's
     * @return if is a valid name
     */
    public static boolean checkName(String name) {
        return ((name.length() < 100 && !name.isEmpty()) && isAlphabetic(name));
    }

    public static String colorGreen(String message) {
        return message = "\u001B[42m" + message + "\u001B[0m";
    }

    public static String colorRed(String message) {
        return message = "\u001B[41m" + message + "\u001B[0m";
    }
}
