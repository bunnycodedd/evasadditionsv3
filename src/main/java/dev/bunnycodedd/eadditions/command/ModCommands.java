package dev.bunnycodedd.eadditions.command;

import com.mojang.brigadier.CommandDispatcher;
import dev.bunnycodedd.eadditions.Config;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import org.apache.commons.lang3.Validate;

public class ModCommands {


    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("meow").executes(context -> {
                    if (context.getSource().isPlayer()) {
                        ServerPlayer p = context.getSource().getPlayer();
                        Validate.notNull(p, "Player cannot be null");
                        p.getServer().getLevel(ServerLevel.OVERWORLD).playSound(null, p.blockPosition(), SoundEvents.CAT_PURREOW, SoundSource.PLAYERS);

                        p.sendSystemMessage(Component.literal("§dYou meowed!"));
                    }
                    return 1;
                })
        );
        dispatcher.register(
                Commands.literal("yip").executes(context -> {
                    if (context.getSource().isPlayer()) {
                        ServerPlayer p = context.getSource().getPlayer();
                        Validate.notNull(p, "Player cannot be null");
                        p.getServer().getLevel(ServerLevel.OVERWORLD).playSound(null, p.blockPosition(), SoundEvents.FOX_AMBIENT, SoundSource.PLAYERS);

                        p.sendSystemMessage(Component.literal("§d*fob noises*"));
                    }
                    return 1;
                })
        );
        dispatcher.register(
                Commands.literal("bark").executes(context -> {
                    if (context.getSource().isPlayer()) {
                        ServerPlayer p = context.getSource().getPlayer();
                        Validate.notNull(p, "Player cannot be null");
                        p.getServer().getLevel(ServerLevel.OVERWORLD).playSound(null, p.blockPosition(), SoundEvents.WOLF_AMBIENT, SoundSource.PLAYERS);

                        p.sendSystemMessage(Component.literal("§dYou barked!"));
                    }
                    return 1;
                })
        );
        dispatcher.register(
                Commands.literal("check-variable")
                        .then(
                                Commands.literal("disableSurfaceSpawning").executes(context -> {
                                    context.getSource().sendSystemMessage(Component.literal(String.valueOf(Config.DISABLE_SURFACE_SPAWNING.get())));
                                    return 1;
                                })
                        )
        );
    }

}