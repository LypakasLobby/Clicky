package com.lypaka.clicky.Utils;

import com.lypaka.clicky.ConfigGetters;
import com.lypaka.lypakautils.MiscHandlers.ItemStackBuilder;
import com.pixelmonmod.pixelmon.api.util.helpers.RandomHelper;
import com.pixelmonmod.pixelmon.items.HeldItem;
import com.pixelmonmod.pixelmon.items.PokeBallItem;
import com.pixelmonmod.pixelmon.items.TechnicalMoveItem;
import com.pixelmonmod.pixelmon.items.heldItems.BerryItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class ItemPools {

    public static List<Item> berries;
    public static List<Item> heldItems;
    public static List<Item> tms;

    public static void loadItemLists() {

        berries = new ArrayList<>();
        heldItems = new ArrayList<>();
        tms = new ArrayList<>();
        ForgeRegistries.ITEMS.getEntries().forEach(entry -> {

            if (entry.getValue() instanceof BerryItem) {

                berries.add(entry.getValue());

            }
            if (entry.getValue() instanceof HeldItem) {

                heldItems.add(entry.getValue());

            }
            if (entry.getValue() instanceof TechnicalMoveItem) {

                tms.add(entry.getValue());

            }

        });

    }

    public static ItemStack getRandomBerry() {

        return new ItemStack(RandomHelper.getRandomElementFromList(berries));

    }

    public static ItemStack getRandomHeldItem() {

        return new ItemStack(RandomHelper.getRandomElementFromList(heldItems));

    }

    public static ItemStack getRandomPokeBall() {

        return ItemStackBuilder.buildFromStringID(RandomHelper.getRandomElementFromList(ConfigGetters.pokeBallList));

    }

    public static ItemStack getRandomTM() {

        return new ItemStack(RandomHelper.getRandomElementFromList(tms));

    }

}
