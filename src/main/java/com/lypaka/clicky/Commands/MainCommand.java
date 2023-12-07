package com.lypaka.clicky.Commands;

import com.lypaka.clicky.GUIs.MainMenu;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;

public class MainCommand {

    public MainCommand (CommandDispatcher<CommandSource> dispatcher) {

        for (String a : ClickyCommand.ALIASES) {

            dispatcher.register(
                    Commands.literal(a)
                            .executes(c -> {

                                if (c.getSource().getEntity() instanceof ServerPlayerEntity) {

                                    ServerPlayerEntity player = (ServerPlayerEntity) c.getSource().getEntity();
                                    MainMenu.open(player);

                                }

                                return 0;

                            })
            );

        }

    }

}
