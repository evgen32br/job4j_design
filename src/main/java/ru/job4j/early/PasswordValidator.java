package ru.job4j.early;

import java.util.Locale;

public class PasswordValidator {

    private static final int MIN = 8;
    private static final int MAX = 32;
    private static final String[] SUBSTRING = {
            "qwerty", "12345",
            "password", "admin", "user"
    };

    public static String validate(String password) {
        char[] pasValidate = password.toCharArray();

        if (password.isEmpty()) {
            throw new IllegalArgumentException("The password does not contain any characters");
        }

        if (password.length() < MIN) {
            throw new IllegalArgumentException("Number of characters less than 8");
        }
        if (password.length() > MAX) {
            throw new IllegalArgumentException("The number of characters is more than 32");
        }

        if (!number(pasValidate)) {
            throw new IllegalArgumentException("The password must contain numbers");
        }

        if (!registerLower(pasValidate)) {
            throw new IllegalArgumentException("The password must contain lowercase");
        }

        if (!registerUpper(pasValidate)) {
            throw new IllegalArgumentException("The password must contain uppercase");
        }

        if (!checkingSymbol(pasValidate)) {
            throw new IllegalArgumentException("The password does not contain any characters");
        }

        if (substring(password)) {
            throw new IllegalArgumentException("The password contains a forbidden substring");
        }
        return password;
    }

    private static Boolean number(char[] password) {
        boolean rsl = false;
        for (char c : password) {
            if (Character.isDigit(c)) {
                rsl = true;
                break;
            }
        }
        return rsl;
    }

    private static Boolean registerLower(char[] password) {
        boolean rsl = false;
        for (char c : password) {
            if (Character.isLowerCase(c)) {
                rsl = true;
                break;
            }
        }
        return rsl;
    }

    private static Boolean registerUpper(char[] password) {
        boolean rsl = false;
        for (char c : password) {
            if (Character.isUpperCase(c)) {
                rsl = true;
                break;
            }
        }
        return rsl;
    }

    private static Boolean checkingSymbol(char[] password) {
        boolean rsl = false;
        for (char c : password) {
            if (!Character.isLetterOrDigit(c)) {
                rsl = true;
                break;
            }
        }
        return rsl;
    }

    private static Boolean substring(String password) {
        boolean rsl = false;
        password = password.toLowerCase(Locale.ROOT);
        for (String s : SUBSTRING) {
            if (password.contains(s)) {
                rsl = true;
                break;
            }
        }
        return rsl;
    }
}