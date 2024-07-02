package org.konceptosociala.komunterio.utils;

import com.jme3.asset.AssetManager;
import com.jme3.audio.AudioNode;
import com.jme3.audio.AudioData.DataType;

public class AudioManager {
    // Music
    public final AudioNode mainTheme;

    // UI
    public final AudioNode buttonClick;
    public final AudioNode buttonHover;

    public AudioManager(AssetManager assetManager) {
        mainTheme = new AudioNode(assetManager, "Music/Chiela Muzikisto.ogg", DataType.Buffer);
        mainTheme.setPositional(false);

        buttonClick = new AudioNode(assetManager, "Sounds/UI/button_click.ogg", DataType.Buffer);
        buttonClick.setPositional(false);
        buttonHover = new AudioNode(assetManager, "Sounds/UI/button_hover.ogg", DataType.Buffer);
        buttonHover.setPositional(false);
    }
}
