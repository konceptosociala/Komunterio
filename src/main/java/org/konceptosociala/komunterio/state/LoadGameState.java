package org.konceptosociala.komunterio.state;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.konceptosociala.komunterio.Komunterio;
import org.konceptosociala.komunterio.game.map.GameMap;
import org.konceptosociala.komunterio.game.map.MapId;
import org.konceptosociala.komunterio.ui.load_game.LoadingBar;
import org.konceptosociala.komunterio.utils.Utils;
import org.konceptosociala.komunterio.utils.load_game.GameLoadType;

import com.jme3.app.Application;
import com.jme3.app.state.BaseAppState;
import com.jme3.asset.AssetManager;
import com.jme3.input.InputManager;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.ImageBuilder;
import de.lessvoid.nifty.builder.LayerBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.ScreenBuilder;
import de.lessvoid.nifty.controls.label.builder.LabelBuilder;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import de.lessvoid.nifty.tools.SizeValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LoadGameState extends BaseAppState implements ScreenController {
    private final GameLoadType loadType;

    private Komunterio app;
    private AssetManager assetManager;
    private InputManager inputManager;
    private Nifty nifty;

    private Element progressBarElement;
    private TextRenderer progressBarText;
    private int frameCount = 0;

    @Override
    protected void initialize(Application app) {
        this.app = (Komunterio) app;
        this.assetManager = this.app.getAssetManager();
        this.inputManager = this.app.getInputManager();
        this.nifty = this.app.getNifty();
    }

    @Override
    protected void onEnable() {
        inputManager.setCursorVisible(false);
        
        nifty.addScreen("load_game", new ScreenBuilder("load_game") {{
            controller(LoadGameState.this);

            layer(new LayerBuilder() {{
                childLayoutCenter();

                image(new ImageBuilder() {{
                    width("100%");
                    height("100%");
                    filename("Interface/Loading/loading_background.png");
                }});

                panel(new PanelBuilder() {{
                    childLayoutVertical();
                    align(Align.Center);
                    valign(VAlign.Center);
                    height("32px");
                    width("70%");

                    control(new LoadingBar("loading_bar") {{
                        align(Align.Center);
                        valign(VAlign.Center);
                        width("100%");
                        height("100%");
                    }});

                    control(new LabelBuilder("loading_text") {{
                        align(Align.Center);
                        font("Interface/Fonts/GNUTypewriter.ttf");
                        text("                                                  ");
                    }});
                }});
            }});
        }}.build(nifty));

        nifty.gotoScreen("load_game");
    }

    @Override
    public void update(float tpf) {
        switch (frameCount) {
            case 1 -> setProgress(0.1f, "test", this::test);
        }

        frameCount++;
    }

    private void test() {
        var mapId = new MapId("kom_test_0");
        var testMap = new GameMap(mapId, assetManager);

        var cube = testMap
            .getScene();

        Utils.LOG.info(cube.getNumControls()+"");

        cube.getUserDataKeys()
            .forEach((key) -> {
                Utils.LOG.info(key+" -> "+cube.getUserData(key));
            });
    }

    private void setProgress(
        final float progress, 
        String loadingText, 
        @Nullable Runnable loadMethod
    ) {
        final int MIN_WIDTH = 32;
        int pixelWidth = (int) (MIN_WIDTH + (progressBarElement.getParent().getWidth() - MIN_WIDTH) * progress);
        progressBarElement.setConstraintWidth(new SizeValue(pixelWidth + "px"));
        progressBarElement.getParent().layoutElements();
        progressBarText.setText(loadingText);

        if (loadMethod != null)
            loadMethod.run();
    }

    @SuppressWarnings("null")
    @Override
    public void bind(@Nonnull Nifty nifty, @Nonnull Screen screen) {
        progressBarElement = screen.findElementById("progress_bar");
        progressBarText = screen
            .findElementById("loading_text")
            .getRenderer(TextRenderer.class);
    }

    @Override
    protected void cleanup(Application app) {
    }

    @Override
    protected void onDisable() {
    }

    @Override
    public void onStartScreen() {
    }

    @Override
    public void onEndScreen() {
    }
}
