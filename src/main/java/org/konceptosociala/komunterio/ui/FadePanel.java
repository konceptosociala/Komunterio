package org.konceptosociala.komunterio.ui;

import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.PanelRenderer;
import de.lessvoid.nifty.tools.Color;
import lombok.NonNull;

public class FadePanel {
    private Element panelElement;
    private float fadeOpacity;

    public FadePanel(@NonNull String id, @NonNull Element parentElement) {
        panelElement = new PanelBuilder(id) {{
            width("100%");
            height("100%");
            backgroundColor(Color.BLACK);
        }}
        .build(parentElement);

        fadeOpacity = 1.0f;
    }

    @SuppressWarnings("null")
    public void reset() {
        fadeOpacity = 1.0f;
        panelElement
            .getRenderer(PanelRenderer.class)
            .setBackgroundColor(Color.BLACK);
    }

    @SuppressWarnings("null")
    public void updateFadeOut() {
        fadeOpacity -= 0.01f;
        panelElement
            .getRenderer(PanelRenderer.class)
            .setBackgroundColor(new Color(0, 0, 0, fadeOpacity));
    }
}
