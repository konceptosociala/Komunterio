package org.konceptosociala.komunterio.ui.main_menu;

import org.konceptosociala.komunterio.ui.WindowPanel;
import org.konceptosociala.komunterio.utils.I18n;

import de.lessvoid.nifty.builder.ElementBuilder.ChildLayoutType;
import de.lessvoid.nifty.elements.Element;
import lombok.NonNull;

public class SelectChapterPanel extends WindowPanel {
    public SelectChapterPanel(
        I18n i18n, 
        @NonNull String id, 
        @NonNull String exitEvent,
        @NonNull String exitHover,
        @NonNull Element parentElement
    ) {
        super(
            i18n.getString("main_menu_select_chapter"), 
            960, 360, 
            ChildLayoutType.Horizontal, 
            id,
            exitEvent, 
            exitHover, 
            parentElement
        );

        add(new ChapterPanel(
            i18n.getString("main_menu_chapter_1"), 
            "Interface/Chapters/chapter_1.png"
        ) {{
            marginLeft("18px");
            marginTop("30px");
        }});

        add(new ChapterPanel(
            i18n.getString("main_menu_chapter_2"), 
            "Interface/Chapters/chapter_1.png"
        ) {{
            marginLeft("30px");
            marginTop("30px");
        }});

        add(new ChapterPanel(
            i18n.getString("main_menu_chapter_3"), 
            "Interface/Chapters/chapter_1.png"
        ) {{
            marginLeft("30px");
            marginTop("30px");
        }});
    }
}
