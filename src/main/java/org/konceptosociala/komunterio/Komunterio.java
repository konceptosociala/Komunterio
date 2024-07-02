package org.konceptosociala.komunterio;

import java.io.IOException;
import java.util.logging.*;
import java.util.Map;
import java.util.HashMap;

import org.konceptosociala.komunterio.state.MainMenuState;
import org.konceptosociala.komunterio.utils.AudioManager;
import org.konceptosociala.komunterio.utils.GameSettings;
import org.konceptosociala.komunterio.utils.I18n;

import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.system.AppSettings;

import de.lessvoid.nifty.Nifty;
import javafx.application.Platform;
import lombok.Getter;

@Getter
public class Komunterio extends SimpleApplication {    
    public static final String SAVING_PATH = "data/Saves/komunterio.sav";

    private Nifty nifty;
    private AudioManager audio;
    private GameSettings gameSettings = new GameSettings("data/settings.toml");
    private I18n i18n = new I18n("data/I18n/"+gameSettings.getTable("general").getString("lang")+".toml");
    
    private MainMenuState mainMenuState;

    public static void main(String[] args) {
        try {
            LogManager.getLogManager().readConfiguration(
                Komunterio.class.getResourceAsStream("/logging.properties")
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        Komunterio app = new Komunterio();
        app.start();
    }

    public Komunterio() {
        var appSettings = new AppSettings(true);

        appSettings.setMinResolution(1024, 768);
        appSettings.setResolution(
            Math.max(gameSettings.getTable("video").getLong("width").intValue(), 1024),
            Math.max(gameSettings.getTable("video").getLong("height").intValue(), 768)
        );

        appSettings.setFullscreen(
            gameSettings.getTable("video").getBoolean("fullscreen")
        );

        if (!gameSettings.getTable("general").getBoolean("debug_mode")) {
            setDisplayStatView(false);
            setDisplayFps(false);
        }

        setShowSettings(false);
        setSettings(appSettings);
    }

    @Override
    public void simpleInitApp() {
        Platform.startup(() -> {});

        initControls();

        nifty = initNifty();
        audio = new AudioManager(assetManager);
        
        mainMenuState = new MainMenuState();
        stateManager.attach(mainMenuState);
    }

    @Override
    public void stop() {
        super.stop();
        Platform.exit();
        System.exit(0);
    }

    private void initControls() {
        inputManager.deleteMapping(SimpleApplication.INPUT_MAPPING_EXIT);

        addMappings(new HashMap<>() {{
            put("ESCAPE", new KeyTrigger(KeyInput.KEY_ESCAPE));
            put("CONSOLE", new KeyTrigger(KeyInput.KEY_APOSTROPHE));

            put("PLAYER_FORWARD", new KeyTrigger(KeyInput.KEY_W));
            put("PLAYER_BACK", new KeyTrigger(KeyInput.KEY_S));
            put("PLAYER_LEFT", new KeyTrigger(KeyInput.KEY_A));
            put("PLAYER_RIGHT", new KeyTrigger(KeyInput.KEY_D));
            put("PLAYER_SLOWDOWN", new KeyTrigger(KeyInput.KEY_LSHIFT));
            put("PLAYER_SPEEDUP", new KeyTrigger(KeyInput.KEY_LCONTROL));
            put("PLAYER_INTERACT", new KeyTrigger(KeyInput.KEY_E));
            put("PLAYER_INVENTORY", new KeyTrigger(KeyInput.KEY_I));
            put("PLAYER_ZOOM", new KeyTrigger(KeyInput.KEY_C));
        }});
    }

    private void addMappings(Map<String, KeyTrigger> mappings) {
        for (var mapping : mappings.entrySet()) {
            inputManager.addMapping(mapping.getKey(), mapping.getValue());
        }
    }

    private Nifty initNifty() {
        NiftyJmeDisplay niftyDisplay = NiftyJmeDisplay.newNiftyJmeDisplay(
            assetManager, 
            inputManager,
            audioRenderer,
            guiViewPort
        );

        Nifty nifty = niftyDisplay.getNifty();
        guiViewPort.addProcessor(niftyDisplay);
        flyCam.setDragToRotate(true);

        nifty.loadStyleFile("nifty-default-styles.xml");
        nifty.loadControlFile("nifty-default-controls.xml");

        nifty.registerEffect("inventory-hint", "org.konceptosociala.kareladventures.ui.inventory.InventoryHint");

        Logger.getLogger("de.lessvoid.nifty").setLevel(Level.SEVERE);
        Logger.getLogger("NiftyInputEventHandlingLog").setLevel(Level.SEVERE);

        return nifty;
    }
}
