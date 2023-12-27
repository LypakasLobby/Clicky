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

public class HeldItemMenu {

    public static void open (ServerPlayerEntity player) {

        ChestTemplate template = ChestTemplate.builder(3).build();
        GooeyPage page = GooeyPage.builder()
                .template(template)
                .title(FancyText.getFormattedString("&4Held Item Clicker"))
                .build();

        int clickButton = 13;

        for (int i = 0; i < 27; i++) {

            if (i != 13) {

                page.getTemplate().getSlot(i).setButton(GlassButton.getGlass(15));

            }

        }

        page.getTemplate().getSlot(clickButton).setButton(getHeldItemButton(player, page));
        UIManager.openUIForcefully(player, page);

    }

    private static Button getHeldItemButton (ServerPlayerEntity player, GooeyPage page) {

        ItemStack choiceBand = ItemStackBuilder.buildFromStringID("pixelmon:choice_band");
        choiceBand.setDisplayName(FancyText.getFormattedText("&eCurrent Clicks: &c" + AccountHandler.getClicks(player, "HeldItem")));
        return GooeyButton.builder()
                .display(choiceBand)
                .onClick(() -> {

                    AccountHandler.updateClicks(player, "HeldItem");
                    if (AccountHandler.getClicks(player, "HeldItem") >= ConfigGetters.heldItemClicks) {

                        player.addItemStackToInventory(ItemPools.getRandomHeldItem());
                        AccountHandler.resetClicks(player, "HeldItem");
                        player.sendMessage(FancyText.getFormattedText("&eYou received a random held item!"), player.getUniqueID());

                    }
                    page.getTemplate().getSlot(13).setButton(getHeldItemButton(player, page));

                })
                .build();

    }

}
