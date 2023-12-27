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

public class PokeBallMenu {

    public static void open (ServerPlayerEntity player) {

        ChestTemplate template = ChestTemplate.builder(3).build();
        GooeyPage page = GooeyPage.builder()
                .template(template)
                .title(FancyText.getFormattedString("&4Poke Ball Clicker"))
                .build();

        int clickButton = 13;

        for (int i = 0; i < 27; i++) {

            if (i != 13) {

                page.getTemplate().getSlot(i).setButton(GlassButton.getGlass(15));

            }

        }

        page.getTemplate().getSlot(clickButton).setButton(getBallButton(player, page));
        UIManager.openUIForcefully(player, page);

    }

    private static Button getBallButton (ServerPlayerEntity player, GooeyPage page) {

        ItemStack ball = ItemStackBuilder.buildFromStringID("pixelmon:poke_ball");
        ball.setDisplayName(FancyText.getFormattedText("&eCurrent Clicks: &c" + AccountHandler.getClicks(player, "PokeBall")));
        return GooeyButton.builder()
                .display(ball)
                .onClick(() -> {

                    AccountHandler.updateClicks(player, "PokeBall");
                    if (AccountHandler.getClicks(player, "PokeBall") >= ConfigGetters.pokeBallClicks) {

                        player.addItemStackToInventory(ItemPools.getRandomPokeBall());
                        AccountHandler.resetClicks(player, "PokeBall");
                        player.sendMessage(FancyText.getFormattedText("&eYou received a random Poke Ball!"), player.getUniqueID());

                    }
                    page.getTemplate().getSlot(13).setButton(getBallButton(player, page));

                })
                .build();

    }

}
