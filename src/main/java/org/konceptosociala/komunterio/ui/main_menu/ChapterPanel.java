package org.konceptosociala.komunterio.ui.main_menu;

import de.lessvoid.nifty.builder.ImageBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.TextBuilder;
import de.lessvoid.nifty.tools.Color;

public class ChapterPanel extends PanelBuilder {
    public ChapterPanel(String title, String bannerPath) {
        childLayoutVertical();
        width("280px");

        image(new ImageBuilder() {{
            filename(bannerPath);
            width("280px");
            height("200px");
        }});

        text(new TextBuilder() {{
            font("Interface/Fonts/GNUTypewriter.ttf");
            width("280px");
            height("24px");
            align(Align.Center);
            color(Color.WHITE);
            marginTop("4px");
            text(title);
        }});
    }
}
