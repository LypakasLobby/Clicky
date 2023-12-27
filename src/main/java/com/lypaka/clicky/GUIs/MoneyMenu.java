package com.lypaka.clicky.GUIs;

import ca.landonjw.gooeylibs2.api.UIManager;
import ca.landonjw.gooeylibs2.api.button.Button;
import ca.landonjw.gooeylibs2.api.button.GooeyButton;
import ca.landonjw.gooeylibs2.api.page.GooeyPage;
import ca.landonjw.gooeylibs2.api.template.types.ChestTemplate;
import com.lypaka.clicky.ConfigGetters;
import com.lypaka.clicky.Utils.AccountHandler;
import com.lypaka.lypakautils.FancyText;
import com.lypaka.lypakautils.MiscHandlers.LogicalPixelmonMoneyHandler;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class MoneyMenu {

    public static void open (ServerPlayerEntity player) {

        ChestTemplate template = ChestTemplate.builder(3).build();
        GooeyPage page = GooeyPage.builder()
                .template(template)
                .title(FancyText.getFormattedString("&4Money Clicker"))
                .build();

        int clickButton = 13;

        for (int i = 0; i < 27; i++) {

            if (i != 13) {

                page.getTemplate().getSlot(i).setButton(GlassButton.getGlass(15));

            }

        }

        page.getTemplate().getSlot(clickButton).setButton(getMoneyButton(player, page));
        UIManager.openUIForcefully(player, page);

    }

    private static Button getMoneyButton (ServerPlayerEntity player, GooeyPage page) {

        ItemStack coins = new ItemStack(Items.GOLD_NUGGET);
        coins.setDisplayName(FancyText.getFormattedText("&eCurrent Clicks: &c" + AccountHandler.getClicks(player, "Money")));
        return GooeyButton.builder()
                .display(coins)
                .onClick(() -> {

                    LogicalPixelmonMoneyHandler.add(player.getUniqueID(), ConfigGetters.moneyClicks);
                    AccountHandler.updateClicks(player, "Money");
                    page.getTemplate().getSlot(13).setButton(getMoneyButton(player, page));

                })
                .build();

    }

}
