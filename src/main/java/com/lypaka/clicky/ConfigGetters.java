package com.lypaka.clicky;

import com.google.common.reflect.TypeToken;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;

import java.util.Map;

public class ConfigGetters {

    public static int eggClicks;
    public static int moneyClicks;
    public static Map<String, Map<String, Integer>> playerStorage;

    public static void load() throws ObjectMappingException {

        eggClicks = Clicky.configManager.getConfigNode(0, "Egg", "Clicks").getInt();
        moneyClicks = Clicky.configManager.getConfigNode(0, "Money", "Amount").getInt();
        playerStorage = Clicky.configManager.getConfigNode(1, "Accounts").getValue(new TypeToken<Map<String, Map<String, Integer>>>() {});

    }

}
