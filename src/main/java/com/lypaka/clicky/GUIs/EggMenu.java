package com.lypaka.clicky.GUIs;

import ca.landonjw.gooeylibs2.api.UIManager;
import ca.landonjw.gooeylibs2.api.button.Button;
import ca.landonjw.gooeylibs2.api.button.GooeyButton;
import ca.landonjw.gooeylibs2.api.page.GooeyPage;
import ca.landonjw.gooeylibs2.api.template.types.ChestTemplate;
import com.lypaka.clicky.ConfigGetters;
import com.lypaka.clicky.Utils.AccountHandler;
import com.lypaka.clicky.Utils.PokemonPool;
import com.lypaka.lypakautils.FancyText;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.storage.PlayerPartyStorage;
import com.pixelmonmod.pixelmon.api.storage.StorageProxy;
import com.pixelmonmod.pixelmon.api.util.helpers.RandomHelper;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.ArrayList;
import java.util.List;

public class EggMenu {

    public static void open (ServerPlayerEntity player) {

        ChestTemplate template = ChestTemplate.builder(3).build();
        GooeyPage page = GooeyPage.builder()
                .template(template)
                .title(FancyText.getFormattedString("&4Egg Clicker"))
                .build();

        int clickButton = 13;

        for (int i = 0; i < 27; i++) {

            if (i != 13) {

                page.getTemplate().getSlot(i).setButton(GlassButton.getGlass(15));

            }

        }

        PlayerPartyStorage storage = StorageProxy.getParty(player);
        page.getTemplate().getSlot(clickButton).setButton(getEggButton(player, storage, page));
        UIManager.openUIForcefully(player, page);

    }

    private static Button getEggButton (ServerPlayerEntity player, PlayerPartyStorage storage, GooeyPage page) {

        ItemStack egg = new ItemStack(Items.EGG);
        egg.setDisplayName(FancyText.getFormattedText("&eCurrent Clicks: &c" + AccountHandler.getEggClicks(player)));
        return GooeyButton.builder()
                .display(egg)
                .onClick(() -> {

                    AccountHandler.updateEggClicks(player);
                    if (AccountHandler.getEggClicks(player) >= ConfigGetters.eggClicks) {

                        List<Pokemon> pool = new ArrayList<>(PokemonPool.pool);
                        Pokemon pokemon = RandomHelper.getRandomElementFromList(pool);
                        pokemon.makeEgg();
                        storage.add(pokemon);
                        AccountHandler.decreaseEggClicks(player);
                        player.sendMessage(FancyText.getFormattedText("&eYou received a random egg!"), player.getUniqueID());

                    }
                    page.getTemplate().getSlot(13).setButton(getEggButton(player, storage, page));

                })
                .build();

    }

}
