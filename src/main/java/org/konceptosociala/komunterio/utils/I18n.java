package org.konceptosociala.komunterio.utils;

import java.io.File;

import com.moandjiezana.toml.Toml;

public class I18n extends Toml {
    public I18n(String i18nPath) {
        try {
            read(new File(i18nPath));
        } catch (Exception e) {
            Utils.LOG.severe(String.format("I18n `%s` is invalid: `%s`", i18nPath, e.getMessage()));
        }
    }

    @Override
    public String getString(String key) {
        var value = super.getString(key);

        if (value == null) {
            Utils.LOG.severe(String.format("Translation key `%s` not found", key));
            return "<undefined>";
        }

        return value;
    }
}
