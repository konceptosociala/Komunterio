package org.konceptosociala.komunterio.utils;

import java.io.File;

import com.moandjiezana.toml.*;

public class GameSettings extends Toml {
    public GameSettings() {
        read(
        """
            [general]
            lang = "en"

            [video]
            width = 1920
            height = 1080
            fullscreen = true

            [sound]
            music = 100
            sounds = 100
        """);
    }

    public GameSettings(String configPath) {
        this();

        try {
            read(new File(configPath));
        } catch (Exception e) {
            Utils.LOG.severe(String.format("Config `%s` is invalid: `%s`", configPath, e.getMessage()));
        }
    }
}
