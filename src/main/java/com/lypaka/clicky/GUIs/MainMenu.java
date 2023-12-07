package com.lypaka.clicky.GUIs;

import ca.landonjw.gooeylibs2.api.UIManager;
import ca.landonjw.gooeylibs2.api.button.Button;
import ca.landonjw.gooeylibs2.api.button.GooeyButton;
import ca.landonjw.gooeylibs2.api.page.GooeyPage;
import ca.landonjw.gooeylibs2.api.template.types.ChestTemplate;
import com.lypaka.clicky.ConfigGetters;
import com.lypaka.clicky.Utils.AccountHandler;
import com.lypaka.lypakautils.FancyText;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.util.text.ITextComponent;

public class MainMenu {

    public static void open (ServerPlayerEntity player) {

        ChestTemplate template = ChestTemplate.builder(3).build();
        GooeyPage page = GooeyPage.builder()
                .template(template)
                .title(FancyText.getFormattedString("&eClicky Menu"))
                .build();

        int[] black = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26};
        int[] blue = new int[]{10, 11, 13, 15, 16};
        int money = 12;
        int eggs = 14;

        for (int i : black) {

            page.getTemplate().getSlot(i).setButton(GlassButton.getGlass(15));

        }

        for (int i : blue) {

            page.getTemplate().getSlot(i).setButton(GlassButton.getGlass(3));

        }

        page.getTemplate().getSlot(money).setButton(getMoneyButton(player));
        page.getTemplate().getSlot(eggs).setButton(getEggButton(player));

        UIManager.openUIForcefully(player, page);

    }

    private static Button getMoneyButton (ServerPlayerEntity player) {

        ItemStack coins = new ItemStack(Items.GOLD_NUGGET);
        coins.setDisplayName(FancyText.getFormattedText("&eMoney Clicker"));
        ListNBT lore = new ListNBT();
        if (ConfigGetters.moneyClicks > 0) {

            lore.add(StringNBT.valueOf(ITextComponent.Serializer.toJson(FancyText.getFormattedText("&eCurrent Clicks: " + AccountHandler.getMoneyClicks(player)))));
            lore.add(StringNBT.valueOf(ITextComponent.Serializer.toJson(FancyText.getFormattedText(""))));
            lore.add(StringNBT.valueOf(ITextComponent.Serializer.toJson(FancyText.getFormattedText("&aClick me to click for money!"))));

        } else {

            lore.add(StringNBT.valueOf(ITextComponent.Serializer.toJson(FancyText.getFormattedText("&cCurrently disabled!"))));

        }
        coins.getOrCreateChildTag("display").put("Lore", lore);
        return GooeyButton.builder()
                .display(coins)
                .onClick(() -> {

                    if (ConfigGetters.moneyClicks > 0) {

                        MoneyMenu.open(player);

                    } else {

                        UIManager.closeUI(player);
                        player.sendMessage(FancyText.getFormattedText("&cThe money clicker is disabled!"), player.getUniqueID());

                    }

                })
                .build();

    }

    private static Button getEggButton (ServerPlayerEntity player) {

        ItemStack eggs = new ItemStack(Items.EGG);
        eggs.setDisplayName(FancyText.getFormattedText("&eEgg Clicker"));
        ListNBT lore = new ListNBT();
        if (ConfigGetters.eggClicks > 0) {

            lore.add(StringNBT.valueOf(ITextComponent.Serializer.toJson(FancyText.getFormattedText("&eCurrent Clicks: " + AccountHandler.getEggClicks(player)))));
            lore.add(StringNBT.valueOf(ITextComponent.Serializer.toJson(FancyText.getFormattedText(""))));
            lore.add(StringNBT.valueOf(ITextComponent.Serializer.toJson(FancyText.getFormattedText("&aClick me to click for a random egg!"))));

        } else {

            lore.add(StringNBT.valueOf(ITextComponent.Serializer.toJson(FancyText.getFormattedText("&cCurrently disabled!"))));

        }
        eggs.getOrCreateChildTag("display").put("Lore", lore);
        return GooeyButton.builder()
                .display(eggs)
                .onClick(() -> {

                    if (ConfigGetters.eggClicks > 0) {

                        EggMenu.open(player);

                    } else {

                        UIManager.closeUI(player);
                        player.sendMessage(FancyText.getFormattedText("&cThe egg clicker is disabled!"), player.getUniqueID());

                    }

                })
                .build();

    }

}
