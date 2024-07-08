package org.konceptosociala.komunterio.state;

import java.io.File;

import javax.annotation.Nonnull;

import org.konceptosociala.komunterio.Komunterio;
import org.konceptosociala.komunterio.ui.FadePanel;
import org.konceptosociala.komunterio.ui.TextButton;
import org.konceptosociala.komunterio.ui.main_menu.*;
import org.konceptosociala.komunterio.utils.AudioManager;
import org.konceptosociala.komunterio.utils.I18n;
import org.konceptosociala.komunterio.utils.load_game.SavedGame;

import com.jme3.app.Application;
import com.jme3.app.state.AppStateManager;
import com.jme3.app.state.BaseAppState;
import com.jme3.input.InputManager;
import com.jme3.input.controls.ActionListener;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.ImageBuilder;
import de.lessvoid.nifty.builder.LayerBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.ScreenBuilder;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

public class MainMenuState extends BaseAppState implements ScreenController, ActionListener {
    private Komunterio app;
    private AppStateManager stateManager;
    private InputManager inputManager;
    private AudioManager audio;
    private Nifty nifty;
    private I18n i18n;

    private FadePanel fadePanel;
    private SelectChapterPanel selectChapterPanel;
    private ControlsPanel controlsPanel;
    private boolean hasSaving;

    @Override
    protected void initialize(Application app) {
        this.app = (Komunterio) app;
        this.stateManager = this.app.getStateManager();
        this.inputManager = this.app.getInputManager();
        this.audio = this.app.getAudio();
        this.nifty = this.app.getNifty();
        this.i18n = this.app.getI18n();
    }

    @SuppressWarnings("null")
    @Override
    protected void onEnable() {
        // audio.mainTheme.play();
        inputManager.setCursorVisible(true);
        inputManager.addListener(this, "ESCAPE");
        hasSaving = new File(Komunterio.SAVING_PATH).exists();

        nifty.addScreen("main_menu", new ScreenBuilder("main_menu") {{
            controller(MainMenuState.this);

            layer(new LayerBuilder("main_layer") {{
                childLayoutCenter();
                backgroundImage("Interface/main_menu_background.jpg");

                panel(new PanelBuilder() {{
                    childLayoutVertical();
                    marginLeft("36px");
                    width("100%");
                    
                    image(new ImageBuilder() {{
                        filename("Interface/main_menu_logo.png");
                    }});

                    panel(new PanelBuilder() {{
                        childLayoutVertical();
                        marginLeft("36px");

                        text(new TextButton(
                            i18n.getString("main_menu_new_game"), 
                            "newGame()", 
                            "hoverSound()"
                        ));

                        text(new TextButton(
                            i18n.getString("main_menu_load_game"), 
                            "loadGame()", 
                            "hoverSound()", 
                            hasSaving
                        ));

                        text(new TextButton(
                            i18n.getString("main_menu_controls"), 
                            "openControls()", 
                            "hoverSound()"
                        ));

                        text(new TextButton(
                            i18n.getString("main_menu_settings"), 
                            "openSettings()", 
                            "hoverSound()",
                            false
                        ));

                        text(new TextButton(
                            i18n.getString("main_menu_exit"), 
                            "exitGame()", 
                            "hoverSound()"
                        ));
                    }});
                }});
            }});

            layer(new LayerBuilder("fade_layer") {{
                childLayoutCenter();
            }});
        }}.build(nifty));

        nifty.gotoScreen("main_menu");

        fadePanel = new FadePanel(
            "fade_panel", 
            nifty
                .getScreen("main_menu")
                .findElementById("fade_layer")
        );

        selectChapterPanel = new SelectChapterPanel(
            i18n,
            "select_chapter_panel", 
            "exitNewGame()",
            "hoverSound()",
            nifty
                .getScreen("main_menu")
                .findElementById("main_layer")
        );

        controlsPanel = new ControlsPanel(
            i18n, 
            "controls_panel",
            "exitControls()", 
            "hoverSound()", 
            nifty
                .getScreen("main_menu")
                .findElementById("main_layer")
        );

        fadePanel.reset();
    }

    // Callback methods

    public void newGame() {
        audio.buttonClick.playInstance();

        // controlsPanel.setVisible(false);
        // selectChapterPanel.setVisible(true);

        setEnabled(false);
        stateManager.attach(new LoadGameState(new SavedGame(Komunterio.SAVING_PATH)));
    }

    public void exitNewGame() {
        audio.buttonClick.playInstance();
        selectChapterPanel.setVisible(false);
    }

    public void loadGame() {
        audio.buttonClick.playInstance();

        setEnabled(false);
        stateManager.attach(new LoadGameState(new SavedGame(Komunterio.SAVING_PATH)));
    }

    public void openControls() {
        audio.buttonClick.playInstance();

        selectChapterPanel.setVisible(false);
        controlsPanel.setVisible(true);
    }

    public void exitControls() {
        audio.buttonClick.playInstance();
        controlsPanel.setVisible(false);
    }

    public void openSettings() {
        audio.buttonClick.playInstance();
    }

    public void exitGame() {
        audio.buttonClick.playInstance();
        app.stop();
    }

    public void hoverSound() {
        audio.buttonHover.playInstance();
    }

    // Other methods

    @Override
    public void onAction(String action, boolean isPressed, float tpf) {
        if (action.equals("ESCAPE") && isPressed) {
            if (selectChapterPanel.isVisible() || controlsPanel.isVisible())
                audio.buttonClick.play();

            selectChapterPanel.setVisible(false);
            controlsPanel.setVisible(false);
        }
    }

    @Override
    public void update(float tpf) {
        fadePanel.updateFadeOut();
    }

    @Override
    protected void onDisable() {
        audio.mainTheme.stop();
        inputManager.removeListener(this);
    }
    
    @Override
    protected void cleanup(Application app) {}

    @Override
    public void bind(@Nonnull Nifty nifty, @Nonnull Screen screen) {}

    @Override
    public void onStartScreen() {}

    @Override
    public void onEndScreen() {}
}
