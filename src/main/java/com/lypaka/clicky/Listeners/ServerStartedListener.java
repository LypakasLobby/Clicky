package com.lypaka.clicky.Listeners;

import com.lypaka.clicky.Clicky;
import com.lypaka.clicky.Utils.PokemonPool;
import com.lypaka.clicky.Utils.SaveTask;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;

@Mod.EventBusSubscriber(modid = Clicky.MOD_ID)
public class ServerStartedListener {

    @SubscribeEvent
    public static void onServerStarted (FMLServerStartedEvent event) {

        PokemonPool.load();
        SaveTask.startTimer();

    }

}
