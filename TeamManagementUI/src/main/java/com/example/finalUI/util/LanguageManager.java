package com.example.finalUI.util;

import java.util.Locale;

public class LanguageManager {
    private static Locale currentLocale = Locale.ENGLISH;

    public static void setLocale(Locale locale) {
        currentLocale = locale;
    }

    public static Locale getLocale() {
        return currentLocale;
    }
}
