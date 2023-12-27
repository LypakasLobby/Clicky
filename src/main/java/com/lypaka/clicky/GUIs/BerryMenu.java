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

public class BerryMenu {

    public static void open (ServerPlayerEntity player) {

        ChestTemplate template = ChestTemplate.builder(3).build();
        GooeyPage page = GooeyPage.builder()
                .template(template)
                .title(FancyText.getFormattedString("&4Berry Clicker"))
                .build();

        int clickButton = 13;

        for (int i = 0; i < 27; i++) {

            if (i != 13) {

                page.getTemplate().getSlot(i).setButton(GlassButton.getGlass(15));

            }

        }

        page.getTemplate().getSlot(clickButton).setButton(getBerryButton(player, page));
        UIManager.openUIForcefully(player, page);

    }

    private static Button getBerryButton (ServerPlayerEntity player, GooeyPage page) {

        ItemStack berry = ItemStackBuilder.buildFromStringID("pixelmon:oran_berry");
        berry.setDisplayName(FancyText.getFormattedText("&eCurrent Clicks: &c" + AccountHandler.getClicks(player, "Berry")));
        return GooeyButton.builder()
                .display(berry)
                .onClick(() -> {

                    AccountHandler.updateClicks(player, "Berry");
                    if (AccountHandler.getClicks(player, "Berry") >= ConfigGetters.berryClicks) {

                        player.addItemStackToInventory(ItemPools.getRandomBerry());
                        AccountHandler.resetClicks(player, "Berry");
                        player.sendMessage(FancyText.getFormattedText("&eYou received a random berry!"), player.getUniqueID());

                    }
                    page.getTemplate().getSlot(13).setButton(getBerryButton(player, page));

                })
                .build();

    }

}
