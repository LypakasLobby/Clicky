package com.lypaka.clicky;

import com.google.common.reflect.TypeToken;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ConfigGetters {

    public static int berryClicks;
    public static int commandClicks;
    public static List<String> commands;
    public static int eggClicks;
    public static double legendaryChance;
    public static double mythicalChance;
    public static double ubChance;
    public static int heldItemClicks;
    public static double moneyClicks;
    public static int pokeBallClicks;
    public static List<String> pokeBallList;
    public static int tmClicks;
    public static Map<String, Map<String, Integer>> playerStorage;

    public static void load() throws ObjectMappingException {

        berryClicks = Clicky.configManager.getConfigNode(0, "Berry", "Amount").getInt();
        commandClicks = Clicky.configManager.getConfigNode(0, "Command", "Amount").getInt();
        commands = Clicky.configManager.getConfigNode(0, "Command", "Commands").getList(TypeToken.of(String.class));
        eggClicks = Clicky.configManager.getConfigNode(0, "Egg", "Clicks").getInt();
        legendaryChance = Clicky.configManager.getConfigNode(0, "Egg", "Legendary-Chance").getDouble();
        mythicalChance = Clicky.configManager.getConfigNode(0, "Egg", "Mythical-Chance").getDouble();
        ubChance = Clicky.configManager.getConfigNode(0, "Egg", "UB-Chance").getDouble();
        heldItemClicks = Clicky.configManager.getConfigNode(0, "Held-Item", "Amount").getInt();
        moneyClicks = Clicky.configManager.getConfigNode(0, "Money", "Amount").getDouble();
        pokeBallClicks = Clicky.configManager.getConfigNode(0, "Poke-Ball", "Amount").getInt();
        pokeBallList = Clicky.configManager.getConfigNode(0, "Poke-Ball", "List").getList(TypeToken.of(String.class));
        tmClicks = Clicky.configManager.getConfigNode(0, "TM", "Amount").getInt();

        playerStorage = Clicky.configManager.getConfigNode(1, "Accounts").getValue(new TypeToken<Map<String, Map<String, Integer>>>() {});

    }

    public static void updateConfigThenLoad() throws ObjectMappingException {

        if (Clicky.configManager.getConfigNode(0, "Berry").isVirtual()) {

            Clicky.configManager.getConfigNode(0, "Berry", "Amount").setValue(150);
            Clicky.configManager.getConfigNode(0, "Command", "Amount").setValue(10000);
            Clicky.configManager.getConfigNode(0, "Command", "Commands").setValue(new ArrayList<>());
            Clicky.configManager.getConfigNode(0, "Egg", "Legendary-Chance").setValue(0);
            Clicky.configManager.getConfigNode(0, "Egg", "Mythical-Chance").setValue(0);
            Clicky.configManager.getConfigNode(0, "Egg", "UB-Chance").setValue(0);
            Clicky.configManager.getConfigNode(0, "Held-Item").setValue(200);
            Clicky.configManager.getConfigNode(0, "Poke-Ball", "Amount").setValue(100);
            List<String> balls = Arrays.asList("pixelmon:poke_ball",
                    "pixelmon:great_ball",
                    "pixelmon:ultra_ball",
                    "pixelmon:feather_ball",
                    "pixelmon:luxury_ball",
                    "pixelmon:nest_ball",
                    "pixelmon:quick_ball",
                    "pixelmon:dusk_ball",
                    "pixelmon:cherish_ball",
                    "pixelmon:timer_ball",
                    "pixelmon:jet_ball",
                    "pixelmon:ancient_poke_ball",
                    "pixelmon:moon_ball",
                    "pixelmon:repeat_ball",
                    "pixelmon:love_ball",
                    "pixelmon:net_ball",
                    "pixelmon:ancient_great_ball",
                    "pixelmon:dive_ball",
                    "pixelmon:strange_ball",
                    "pixelmon:level_ball",
                    "pixelmon:sport_ball",
                    "pixelmon:ancient_ultra_ball",
                    "pixelmon:gigaton_ball",
                    "pixelmon:safari_ball",
                    "pixelmon:heavy_ball",
                    "pixelmon:ancient_heavy_ball",
                    "pixelmon:dream_ball",
                    "pixelmon:fast_ball",
                    "pixelmon:heal_ball",
                    "pixelmon:beast_ball",
                    "pixelmon:origin_ball",
                    "pixelmon:leaden_ball",
                    "pixelmon:lure_ball",
                    "pixelmon:gs_ball",
                    "pixelmon:friend_ball",
                    "pixelmon:christmas_ball",
                    "pixelmon:wing_ball",
                    "pixelmon:premier_ball",
                    "pixelmon:park_ball",
                    "pixelmon:master_ball");
            Clicky.configManager.getConfigNode(0, "Poke-Ball", "List").setValue(balls);
            Clicky.configManager.getConfigNode(0, "TM", "Amount").setValue(200);
            Clicky.configManager.save();

        }

        load();

    }

}
