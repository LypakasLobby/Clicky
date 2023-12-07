package com.lypaka.clicky.GUIs;

import ca.landonjw.gooeylibs2.api.button.Button;
import ca.landonjw.gooeylibs2.api.button.GooeyButton;
import com.lypaka.lypakautils.MiscHandlers.ItemStackBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GlassButton {

    public static Button getGlass (int color) {

        String colorName = "black";
        if (color == 3) colorName = "light_blue";
        ItemStack glass = ItemStackBuilder.buildFromStringID("minecraft:" + colorName + "_stained_glass_pane");
        return GooeyButton.builder().display(glass).build();

    }

}
