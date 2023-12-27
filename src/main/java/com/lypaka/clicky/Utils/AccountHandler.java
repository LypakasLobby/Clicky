package com.lypaka.clicky.Utils;

import com.lypaka.clicky.ConfigGetters;
import net.minecraft.entity.player.ServerPlayerEntity;

import java.util.HashMap;
import java.util.Map;

public class AccountHandler {

    public static int getClicks (ServerPlayerEntity player, String type) {

        int clicks = 0;
        if (ConfigGetters.playerStorage.containsKey(player.getUniqueID().toString())) {

            Map<String, Integer> map = ConfigGetters.playerStorage.get(player.getUniqueID().toString());
            if (map.containsKey(type)) {

                clicks = map.get(type);

            }

        }

        return clicks;

    }

    public static void updateClicks (ServerPlayerEntity player, String type) {

        Map<String, Integer> clickMap = new HashMap<>();
        if (ConfigGetters.playerStorage.containsKey(player.getUniqueID().toString())) {

            clickMap = ConfigGetters.playerStorage.get(player.getUniqueID().toString());
        }
        int clicks = clickMap.getOrDefault(type, 0);
        int updated = clicks + 1;
        clickMap.put(type, updated);
        ConfigGetters.playerStorage.put(player.getUniqueID().toString(), clickMap);

    }

    public static void resetClicks (ServerPlayerEntity player, String type) {

        ConfigGetters.playerStorage.get(player.getUniqueID().toString()).entrySet().removeIf(e -> e.getKey().equalsIgnoreCase(type));

    }

}
