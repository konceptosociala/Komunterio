package org.konceptosociala.komunterio.ui;

import de.lessvoid.nifty.builder.HoverEffectBuilder;
import de.lessvoid.nifty.builder.TextBuilder;
import de.lessvoid.nifty.tools.Color;

public class TextButton extends TextBuilder {
    public TextButton(String id, String label, String onClick, String onHover, boolean enabled) {
        if (id != null)
            id(id);

        font("Interface/Fonts/GNUTypewriter.ttf");
        height("24px");
        text(label);

        visibleToMouse(true);
        focusable(false);

        if (enabled) {
            color(Color.WHITE);
            interactOnClick(onClick);

            onHoverEffect(new HoverEffectBuilder("textColor") {{
                effectParameter("color", "#888f");
                onStartEffectCallback(onHover);
            }});
        } else {
            color("#888f");
        }
    }

    public TextButton(String id, String label, String onClick, String onHover) {
        this(id, label, onClick, onHover, true);
    }

    public TextButton(String label, String onClick, String onHover, boolean enabled) {
        this(null, label, onClick, onHover, enabled);
    }

    public TextButton(String label, String onClick, String onHover) {
        this(null, label, onClick, onHover, true);
    }
}
