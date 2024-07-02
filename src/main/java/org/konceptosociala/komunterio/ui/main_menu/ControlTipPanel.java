package org.konceptosociala.komunterio.ui.main_menu;

import de.lessvoid.nifty.builder.ImageBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.TextBuilder;
import de.lessvoid.nifty.tools.Color;

public class ControlTipPanel extends PanelBuilder {
    public ControlTipPanel(String keyIcon, String tipText) {
        childLayoutHorizontal();
        width("320px");

        image(new ImageBuilder() {{
            filename("Interface/GuideBook/Keyboard/"+keyIcon+".png");
        }});

        text(new TextBuilder() {{
            font("Interface/Fonts/GNUTypewriter.ttf");
            height("64px");
            align(Align.Center);
            valign(VAlign.Center);
            color(Color.WHITE);
            text("- "+tipText);
        }});
    }
}