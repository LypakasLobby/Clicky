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
        int berry = 10;
        int command = 11;
        int eggs = 12;
        int heldItem = 13;
        int money = 14;
        int pokeBall = 15;
        int tm = 16;

        for (int i : black) {

            page.getTemplate().getSlot(i).setButton(GlassButton.getGlass(15));

        }

        page.getTemplate().getSlot(tm).setButton(getTMButton(player));
        page.getTemplate().getSlot(pokeBall).setButton(getPokeBallButton(player));
        page.getTemplate().getSlot(heldItem).setButton(getHeldItemButton(player));
        page.getTemplate().getSlot(command).setButton(getCommandButton(player));
        page.getTemplate().getSlot(berry).setButton(getBerryButton(player));
        page.getTemplate().getSlot(eggs).setButton(getEggButton(player));
        page.getTemplate().getSlot(money).setButton(getMoneyButton(player));

        UIManager.openUIForcefully(player, page);

    }

    public static Button getTMButton (ServerPlayerEntity player) {

        ItemStack tm = ItemStackBuilder.buildFromStringID("pixelmon:tm9_blank");
        tm.setDisplayName(FancyText.getFormattedText("&eTM/TR Clicker"));
        ListNBT lore = new ListNBT();
        if (ConfigGetters.tmClicks > 0) {

            lore.add(StringNBT.valueOf(ITextComponent.Serializer.toJson(FancyText.getFormattedText("&eCurrent Clicks: " + AccountHandler.getClicks(player, "TM")))));
            lore.add(StringNBT.valueOf(ITextComponent.Serializer.toJson(FancyText.getFormattedText(""))));
            lore.add(StringNBT.valueOf(ITextComponent.Serializer.toJson(FancyText.getFormattedText("&aClick me to click for a random TM/TR!"))));

        } else {

            lore.add(StringNBT.valueOf(ITextComponent.Serializer.toJson(FancyText.getFormattedText("&cCurrently disabled!"))));

        }
        tm.getOrCreateChildTag("display").put("Lore", lore);
        return GooeyButton.builder()
                .display(tm)
                .onClick(() -> {

                    if (ConfigGetters.tmClicks > 0) {

                        TMMenu.open(player);

                    } else {

                        UIManager.closeUI(player);
                        player.sendMessage(FancyText.getFormattedText("&cThe TM/TR clicker is disabled!"), player.getUniqueID());

                    }

                })
                .build();

    }

    public static Button getPokeBallButton (ServerPlayerEntity player) {

        ItemStack pokeBall = ItemStackBuilder.buildFromStringID("pixelmon:poke_ball");
        pokeBall.setDisplayName(FancyText.getFormattedText("&ePoke Ball Clicker"));
        ListNBT lore = new ListNBT();
        if (ConfigGetters.pokeBallClicks > 0) {

            lore.add(StringNBT.valueOf(ITextComponent.Serializer.toJson(FancyText.getFormattedText("&eCurrent Clicks: " + AccountHandler.getClicks(player, "PokeBall")))));
            lore.add(StringNBT.valueOf(ITextComponent.Serializer.toJson(FancyText.getFormattedText(""))));
            lore.add(StringNBT.valueOf(ITextComponent.Serializer.toJson(FancyText.getFormattedText("&aClick me to click for a random Poke Ball!"))));

        } else {

            lore.add(StringNBT.valueOf(ITextComponent.Serializer.toJson(FancyText.getFormattedText("&cCurrently disabled!"))));

        }
        pokeBall.getOrCreateChildTag("display").put("Lore", lore);
        return GooeyButton.builder()
                .display(pokeBall)
                .onClick(() -> {

                    if (ConfigGetters.pokeBallClicks > 0) {

                        PokeBallMenu.open(player);

                    } else {

                        UIManager.closeUI(player);
                        player.sendMessage(FancyText.getFormattedText("&cThe Poke Ball clicker is disabled!"), player.getUniqueID());

                    }

                })
                .build();

    }

    public static Button getHeldItemButton (ServerPlayerEntity player) {

        ItemStack choiceBand = ItemStackBuilder.buildFromStringID("pixelmon:choice_band");
        choiceBand.setDisplayName(FancyText.getFormattedText("&eHeld Item Clicker"));
        ListNBT lore = new ListNBT();
        if (ConfigGetters.heldItemClicks > 0) {

            lore.add(StringNBT.valueOf(ITextComponent.Serializer.toJson(FancyText.getFormattedText("&eCurrent Clicks: " + AccountHandler.getClicks(player, "HeldItem")))));
            lore.add(StringNBT.valueOf(ITextComponent.Serializer.toJson(FancyText.getFormattedText(""))));
            lore.add(StringNBT.valueOf(ITextComponent.Serializer.toJson(FancyText.getFormattedText("&aClick me to click for a random held item!"))));

        } else {

            lore.add(StringNBT.valueOf(ITextComponent.Serializer.toJson(FancyText.getFormattedText("&cCurrently disabled!"))));

        }
        choiceBand.getOrCreateChildTag("display").put("Lore", lore);
        return GooeyButton.builder()
                .display(choiceBand)
                .onClick(() -> {

                    if (ConfigGetters.heldItemClicks > 0) {

                        HeldItemMenu.open(player);

                    } else {

                        UIManager.closeUI(player);
                        player.sendMessage(FancyText.getFormattedText("&cThe held item clicker is disabled!"), player.getUniqueID());

                    }

                })
                .build();

    }

    public static Button getCommandButton (ServerPlayerEntity player) {

        ItemStack paper = new ItemStack(Items.PAPER);
        paper.setDisplayName(FancyText.getFormattedText("&eCommand Clicker"));
        ListNBT lore = new ListNBT();
        if (ConfigGetters.commandClicks > 0 && ConfigGetters.commands.size() > 0) {

            lore.add(StringNBT.valueOf(ITextComponent.Serializer.toJson(FancyText.getFormattedText("&eCurrent Clicks: " + AccountHandler.getClicks(player, "Commands")))));
            lore.add(StringNBT.valueOf(ITextComponent.Serializer.toJson(FancyText.getFormattedText(""))));
            lore.add(StringNBT.valueOf(ITextComponent.Serializer.toJson(FancyText.getFormattedText("&aClick me to click for commands!"))));

        } else {

            lore.add(StringNBT.valueOf(ITextComponent.Serializer.toJson(FancyText.getFormattedText("&cCurrently disabled!"))));

        }
        paper.getOrCreateChildTag("display").put("Lore", lore);
        return GooeyButton.builder()
                .display(paper)
                .onClick(() -> {

                    if (ConfigGetters.commandClicks > 0 && ConfigGetters.commands.size() > 0) {

                        CommandMenu.open(player);

                    } else {

                        UIManager.closeUI(player);
                        player.sendMessage(FancyText.getFormattedText("&cThe command clicker is disabled!"), player.getUniqueID());

                    }

                })
                .build();

    }

    public static Button getBerryButton (ServerPlayerEntity player) {

        ItemStack berry = ItemStackBuilder.buildFromStringID("pixelmon:oran_berry");
        berry.setDisplayName(FancyText.getFormattedText("&eBerry Clicker"));
        ListNBT lore = new ListNBT();
        if (ConfigGetters.berryClicks > 0) {

            lore.add(StringNBT.valueOf(ITextComponent.Serializer.toJson(FancyText.getFormattedText("&eCurrent Clicks: " + AccountHandler.getClicks(player, "Berry")))));
            lore.add(StringNBT.valueOf(ITextComponent.Serializer.toJson(FancyText.getFormattedText(""))));
            lore.add(StringNBT.valueOf(ITextComponent.Serializer.toJson(FancyText.getFormattedText("&aClick me to click for berries!"))));

        } else {

            lore.add(StringNBT.valueOf(ITextComponent.Serializer.toJson(FancyText.getFormattedText("&cCurrently disabled!"))));

        }
        berry.getOrCreateChildTag("display").put("Lore", lore);
        return GooeyButton.builder()
                .display(berry)
                .onClick(() -> {

                    if (ConfigGetters.berryClicks > 0) {

                        BerryMenu.open(player);

                    } else {

                        UIManager.closeUI(player);
                        player.sendMessage(FancyText.getFormattedText("&cThe berry clicker is disabled!"), player.getUniqueID());

                    }

                })
                .build();

    }

    private static Button getMoneyButton (ServerPlayerEntity player) {

        ItemStack coins = new ItemStack(Items.GOLD_NUGGET);
        coins.setDisplayName(FancyText.getFormattedText("&eMoney Clicker"));
        ListNBT lore = new ListNBT();
        if (ConfigGetters.moneyClicks > 0) {

            lore.add(StringNBT.valueOf(ITextComponent.Serializer.toJson(FancyText.getFormattedText("&eCurrent Clicks: " + AccountHandler.getClicks(player, "Money")))));
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

            lore.add(StringNBT.valueOf(ITextComponent.Serializer.toJson(FancyText.getFormattedText("&eCurrent Clicks: " + AccountHandler.getClicks(player, "Egg")))));
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
