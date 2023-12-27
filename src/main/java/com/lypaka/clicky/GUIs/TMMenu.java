package com.lypaka.clicky.GUIs;

import ca.landonjw.gooeylibs2.api.UIManager;
import ca.landonjw.gooeylibs2.api.button.Button;
import ca.landonjw.gooeylibs2.api.button.GooeyButton;
import ca.landonjw.gooeylibs2.api.page.GooeyPage;
import ca.landonjw.gooeylibs2.api.template.types.ChestTemplate;
import com.lypaka.clicky.ConfigGetters;
import com.lypaka.clicky.Utils.AccountHandler;
import com.lypaka.clicky.Utils.ItemPools;
import com.lypaka.lypakautils.FancyText;
import com.lypaka.lypakautils.MiscHandlers.ItemStackBuilder;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;

public class TMMenu {

    public static void open (ServerPlayerEntity player) {

        ChestTemplate template = ChestTemplate.builder(3).build();
        GooeyPage page = GooeyPage.builder()
                .template(template)
                .title(FancyText.getFormattedString("&4TM/TR Clicker"))
                .build();

        int clickButton = 13;

        for (int i = 0; i < 27; i++) {

            if (i != 13) {

                page.getTemplate().getSlot(i).setButton(GlassButton.getGlass(15));

            }

        }

        page.getTemplate().getSlot(clickButton).setButton(getTMButton(player, page));
        UIManager.openUIForcefully(player, page);

    }

    private static Button getTMButton (ServerPlayerEntity player, GooeyPage page) {

        ItemStack tm = ItemStackBuilder.buildFromStringID("pixelmon:tm9_blank");
        tm.setDisplayName(FancyText.getFormattedText("&eCurrent Clicks: &c" + AccountHandler.getClicks(player, "TM")));
        return GooeyButton.builder()
                .display(tm)
                .onClick(() -> {

                    AccountHandler.updateClicks(player, "TM");
                    if (AccountHandler.getClicks(player, "TM") >= ConfigGetters.tmClicks) {

                        player.addItemStackToInventory(ItemPools.getRandomTM());
                        AccountHandler.resetClicks(player, "TM");
                        player.sendMessage(FancyText.getFormattedText("&eYou received a random TM!"), player.getUniqueID());

                    }
                    page.getTemplate().getSlot(13).setButton(getTMButton(player, page));

                })
                .build();

    }

}
