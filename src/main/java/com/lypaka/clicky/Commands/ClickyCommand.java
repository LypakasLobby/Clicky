package com.lypaka.clicky.Commands;

import com.lypaka.clicky.Clicky;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

import java.util.Arrays;
import java.util.List;

@Mod.EventBusSubscriber(modid = Clicky.MOD_ID)
public class ClickyCommand {

    public static final List<String> ALIASES = Arrays.asList("clicky", "click", "clicks");

    @SubscribeEvent
    public static void onCommandRegistration (RegisterCommandsEvent event) {

        new MainCommand(event.getDispatcher());
        new ReloadCommand(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());

    }

}
