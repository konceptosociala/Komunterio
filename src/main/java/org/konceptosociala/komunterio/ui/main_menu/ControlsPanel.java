package org.konceptosociala.komunterio.ui.main_menu;

import org.konceptosociala.komunterio.ui.WindowPanel;
import org.konceptosociala.komunterio.utils.I18n;

import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.ElementBuilder.ChildLayoutType;
import de.lessvoid.nifty.elements.Element;
import lombok.NonNull;

public class ControlsPanel extends WindowPanel {
    public ControlsPanel(
        I18n i18n, 
        @NonNull String id, 
        @NonNull String exitEvent,
        @NonNull String exitHover,
        @NonNull Element parentElement
    ) {
        super(
            i18n.getString("main_menu_controls"), 
            720, 540, 
            ChildLayoutType.Horizontal, 
            id,
            exitEvent, 
            exitHover, 
            parentElement
        );

        add(new PanelBuilder() {{
            childLayoutVertical();
            marginLeft("12px");
            marginTop("30px");

            panel(new ControlTipPanel("keyboard_apostrophe_outline", i18n.getString("main_menu_controls_open_console")));
            panel(new ControlTipPanel("keyboard_w_outline", i18n.getString("main_menu_controls_move_forward")));
            panel(new ControlTipPanel("keyboard_s_outline", i18n.getString("main_menu_controls_move_back")));
            panel(new ControlTipPanel("keyboard_a_outline", i18n.getString("main_menu_controls_move_left")));
            panel(new ControlTipPanel("keyboard_d_outline", i18n.getString("main_menu_controls_move_right")));
            panel(new ControlTipPanel("keyboard_shift_outline", i18n.getString("main_menu_controls_slowdown")));
            panel(new ControlTipPanel("keyboard_ctrl_outline", i18n.getString("main_menu_controls_speedup")));
        }});

        add(new PanelBuilder() {{
            childLayoutVertical();
            marginLeft("12px");
            marginTop("30px");

            panel(new ControlTipPanel("keyboard_e_outline", i18n.getString("main_menu_controls_interact")));
            panel(new ControlTipPanel("keyboard_i_outline", i18n.getString("main_menu_controls_inventory")));
            panel(new ControlTipPanel("keyboard_c_outline", i18n.getString("main_menu_controls_zoom")));
            panel(new ControlTipPanel("keyboard_f6_outline", i18n.getString("main_menu_controls_save")));
            panel(new ControlTipPanel("keyboard_f9_outline", i18n.getString("main_menu_controls_load")));
        }});
    }
}
