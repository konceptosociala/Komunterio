package org.konceptosociala.komunterio.ui.load_game;

import de.lessvoid.nifty.builder.ControlBuilder;
import de.lessvoid.nifty.builder.ImageBuilder;

public class LoadingBar extends ControlBuilder {
    public LoadingBar(String name) {
        super(name);
        image(new ImageBuilder() {{
            filename("Interface/Loading/loading_bar_outer.png");
            childLayoutAbsolute();
            imageMode("resize:15,2,15,15,15,2,15,2,15,2,15,15");

            image(new ImageBuilder("progress_bar") {{
                x("0");
                y("0");
                filename("Interface/Loading/loading_bar_inner.png");
                width("32px");
                height("100%");
                imageMode("resize:15,2,15,15,15,2,15,2,15,2,15,15");
            }});
        }});
    }
}
