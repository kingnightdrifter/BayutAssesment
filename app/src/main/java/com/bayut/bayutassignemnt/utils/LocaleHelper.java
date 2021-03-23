package com.bayut.bayutassignemnt.utils;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;

import java.util.Locale;


/**
 * LocaleHelper is used to change App's Locale and Persist this change for the next time
 */

public class LocaleHelper extends ContextWrapper {

    public LocaleHelper(Context base) {
        super(base);
    }

    @SuppressLint("NewApi")
    @SuppressWarnings("deprecation")
    public static ContextWrapper wrapLocale(Context context, String language) {
        // Get config
        Configuration config = context.getResources().getConfiguration();

        if (!"".equalsIgnoreCase(language)) {

            // Set new Locale as Default
            Locale newLocale = new Locale(language);
            Locale.setDefault(newLocale);

            Resources res = context.getResources();
            Configuration configuration = res.getConfiguration();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                setSystemLocale(config, newLocale);

                configuration.setLocale(newLocale);

                LocaleList localeList = new LocaleList(newLocale);
                LocaleList.setDefault(localeList);
                configuration.setLocales(localeList);

                context = context.createConfigurationContext(configuration);

            } else {
                setSystemLocaleLegacy(config, newLocale);

                configuration.setLocale(newLocale);
                context = context.createConfigurationContext(configuration);

            }

        }
        return new LocaleHelper(context);
    }

    @SuppressWarnings("deprecation")
    public static Locale getSystemLocaleLegacy(Configuration config) {
        return config.locale;
    }

    @TargetApi(Build.VERSION_CODES.N)
    public static Locale getSystemLocale(Configuration config) {
        return config.getLocales().get(0);
    }

    @SuppressWarnings("deprecation")
    public static void setSystemLocaleLegacy(Configuration config, Locale locale) {
        config.locale = locale;
    }

    @TargetApi(Build.VERSION_CODES.N)
    public static void setSystemLocale(Configuration config, Locale locale) {
        config.setLocale(locale);
    }
}

