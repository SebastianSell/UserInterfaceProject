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

    /**
     * Sets the current locale used by the application.
     *
     * @param locale the {@link Locale} to set as the current locale
     */
    public static void setLocale(Locale locale) {
        currentLocale = locale;
    }

    /**
     * Retrieves the current locale used by the application.
     *
     * @return the current {@link Locale}
     */
    public static Locale getLocale() {
        return currentLocale;
    }
}
