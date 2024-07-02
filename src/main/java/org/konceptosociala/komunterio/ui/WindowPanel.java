package org.konceptosociala.komunterio.ui;

import de.lessvoid.nifty.builder.ElementBuilder;
import de.lessvoid.nifty.builder.ImageBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.TextBuilder;
import de.lessvoid.nifty.builder.ElementBuilder.ChildLayoutType;
import de.lessvoid.nifty.builder.HoverEffectBuilder;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.tools.Color;
import lombok.NonNull;

public class WindowPanel{
    private String id;
    private Element panelElement;

    public WindowPanel(
        String title,
        int width,
        int height,
        @NonNull ChildLayoutType childLayout,
        @NonNull String id, 
        @NonNull String exitEvent,
        @NonNull String exitHover,
        @NonNull Element parentElement
    ) {
        this.id = id;
        this.panelElement = new PanelBuilder(id) {{
            childLayoutCenter();
            width("100%");
            height("100%");
            visible(false);

            image(new ImageBuilder() {{
                filename("Interface/UI/window.png");
                width(width+"px");
                height(height+"px");
                imageMode("resize:36,184,36,36,36,184,36,184,36,184,36,36");
            }});

            panel(new PanelBuilder() {{
                childLayoutVertical();
                width(width+"px");
                height(height+"px");
                paddingLeft("12px");
                paddingRight("12px");

                panel(new PanelBuilder() {{
                    childLayoutHorizontal();
                    height("24px");
                    
                    text(new TextBuilder(id+"_title") {{
                        font("Interface/Fonts/GNUTypewriter.ttf");
                        height("24px");
                        color(Color.WHITE);
                        text(title);
                        marginTop("4px");
                        marginLeft("18px");
                        marginBottom("16px");
                    }});

                    panel(new PanelBuilder(id+"_exit") {{
                        childLayoutCenter();
                        width("12px");
                        height("12px");
                        marginTop("10px");
                        marginLeft((width-(title.length()*14))+"px");

                        visibleToMouse(true);
                        interactOnClick(exitEvent);

                        onHoverEffect(new HoverEffectBuilder("alphaHide") {{
                            onStartEffectCallback(exitHover);
                        }});
                    }});
                }});

                panel(new PanelBuilder(id+"_elements_panel") {{
                    childLayout(childLayout);
                    width("100%");
                    height("100%");
                }});
            }});
        }}.build(parentElement);
    }

    public void setVisible(boolean visible) {
        panelElement.setVisible(visible);
    }

    public boolean isVisible() {
        return panelElement.isVisible();
    }

    @SuppressWarnings("null")
    public void add(ElementBuilder elementBuilder) {
        elementBuilder.build(
            panelElement.findElementById(id+"_elements_panel")
        );
    }
}
