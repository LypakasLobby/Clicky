package com.lypaka.clicky.Utils;

import com.lypaka.clicky.ConfigGetters;
import net.minecraft.entity.player.ServerPlayerEntity;

import java.util.HashMap;
import java.util.Map;

public class AccountHandler {

    public static void updateEggClicks (ServerPlayerEntity player) {

        Map<String, Integer> clicks = new HashMap<>();
        if (ConfigGetters.playerStorage.containsKey(player.getUniqueID().toString())) {

            clicks = ConfigGetters.playerStorage.get(player.getUniqueID().toString());

        }

        int eggClicks = 0;
        if (clicks.containsKey("Egg")) {

            eggClicks = clicks.get("Egg");

        }

        int updated = eggClicks + 1;
        clicks.put("Egg", updated);
        ConfigGetters.playerStorage.put(player.getUniqueID().toString(), clicks);

    }

    public static void updateMoneyClicks (ServerPlayerEntity player) {

        Map<String, Integer> clicks = new HashMap<>();
        if (ConfigGetters.playerStorage.containsKey(player.getUniqueID().toString())) {

            clicks = ConfigGetters.playerStorage.get(player.getUniqueID().toString());

        }

        int moneyClicks = 0;
        if (clicks.containsKey("Money")) {

            moneyClicks = clicks.get("Money");

        }

        int updated = moneyClicks + 1;
        clicks.put("Money", updated);
        ConfigGetters.playerStorage.put(player.getUniqueID().toString(), clicks);

    }

    public static int getEggClicks (ServerPlayerEntity player) {

        int clicks = 0;
        if (ConfigGetters.playerStorage.containsKey(player.getUniqueID().toString())) {

            Map<String, Integer> clickMap = ConfigGetters.playerStorage.get(player.getUniqueID().toString());
            if (clickMap.containsKey("Egg")) {

                clicks = clickMap.get("Egg");

            }

        }

        return clicks;

    }

    public static void decreaseEggClicks (ServerPlayerEntity player) {

        Map<String, Integer> map = ConfigGetters.playerStorage.get(player.getUniqueID().toString());
        int current = map.get("Egg");
        int updated = current - ConfigGetters.eggClicks;
        map.put("Egg", updated);

    }

    public static int getMoneyClicks (ServerPlayerEntity player) {

        int clicks = 0;
        if (ConfigGetters.playerStorage.containsKey(player.getUniqueID().toString())) {

            Map<String, Integer> clickMap = ConfigGetters.playerStorage.get(player.getUniqueID().toString());
            if (clickMap.containsKey("Money")) {

                clicks = clickMap.get("Money");

            }

        }

        return clicks;

    }

}
