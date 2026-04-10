package com.example.finalUI.util;

import java.util.Locale;
/**
 * course code: cst8412
 *
 *
 * This class manages the language for the UI of the project.
 *
 *
 * @author Sebastian Sell, Luca Beumer, Bennet Ireland
 * @version 1.0
 */
public class LanguageManager {
    private static Locale currentLocale = Locale.ENGLISH;

    public static void setLocale(Locale locale) {
        currentLocale = locale;
    }

    public static Locale getLocale() {
        return currentLocale;
    }
}
