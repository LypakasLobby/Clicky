package com.lypaka.clicky.GUIs;

import ca.landonjw.gooeylibs2.api.UIManager;
import ca.landonjw.gooeylibs2.api.button.Button;
import ca.landonjw.gooeylibs2.api.button.GooeyButton;
import ca.landonjw.gooeylibs2.api.page.GooeyPage;
import ca.landonjw.gooeylibs2.api.template.types.ChestTemplate;
import com.lypaka.clicky.ConfigGetters;
import com.lypaka.clicky.Utils.AccountHandler;
import com.lypaka.lypakautils.FancyText;
import com.lypaka.lypakautils.MiscHandlers.ItemStackBuilder;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;

public class CommandMenu {

    public static void open (ServerPlayerEntity player) {

        ChestTemplate template = ChestTemplate.builder(3).build();
        GooeyPage page = GooeyPage.builder()
                .template(template)
                .title(FancyText.getFormattedString("&4Command Clicker"))
                .build();

        int clickButton = 13;

        for (int i = 0; i < 27; i++) {

            if (i != 13) {

                page.getTemplate().getSlot(i).setButton(GlassButton.getGlass(15));

            }

        }

        page.getTemplate().getSlot(clickButton).setButton(getCommandButton(player, page));
        UIManager.openUIForcefully(player, page);

    }

    private static Button getCommandButton (ServerPlayerEntity player, GooeyPage page) {

        ItemStack paper = ItemStackBuilder.buildFromStringID("minecraft:paper");
        paper.setDisplayName(FancyText.getFormattedText("&eCurrent Clicks: &c" + AccountHandler.getClicks(player, "Command")));
        return GooeyButton.builder()
                .display(paper)
                .onClick(() -> {

                    AccountHandler.updateClicks(player, "Command");
                    if (AccountHandler.getClicks(player, "Command") >= ConfigGetters.commandClicks) {

                        AccountHandler.resetClicks(player, "Command");
                        for (String c : ConfigGetters.commands) {

                            player.getServer().getCommandManager().handleCommand(player.getServer().getCommandSource(), c.replace("%player%", player.getName().getString()));

                        }

                    }
                    page.getTemplate().getSlot(13).setButton(getCommandButton(player, page));

                })
                .build();

    }

}
