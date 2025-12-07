package dev.bunnycodedd.eadditions.block;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITag;

public class AdminCommands {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("admin").requires(stack -> {
                            return stack.hasPermission(2);
                        })
                        .then(
                                Commands.literal("list_tags").then(
                                        Commands.literal("block").then(
                                                Commands.argument("pos", BlockPosArgument.blockPos()).executes(context -> {
                                                    ServerPlayer p = context.getSource().getPlayer();
                                                    if (p == null) {
                                                        context.getSource().sendFailure(Component.literal("You are not a player."));
                                                        return -1;
                                                    }
                                                    BlockPos pos = BlockPosArgument.getBlockPos(context, "pos");
                                                    ServerLevel level = context.getSource().getLevel();
                                                    Block block = level.getBlockState(pos).getBlock();

                                                    p.sendSystemMessage(
                                                            Component
                                                                    .literal("The Block ")
                                                                    .append(block.getName())
                                                                    .append(" has the following block tags: ")
                                                                    .withStyle(ChatFormatting.GOLD)
                                                    );


                                                    for (ITag<Block> tag : ForgeRegistries.BLOCKS.tags()) {
                                                        if (tag.contains(block)) {
                                                            p.sendSystemMessage(Component.literal("   " +
                                                                            tag.getKey().location())
                                                                    .withStyle(ChatFormatting.GRAY));
                                                        }
                                                    }

                                                    return 1;
                                                })
                                        )
                                ).then(
                                        Commands.literal("item").executes(context -> {
                                            ServerPlayer p = context.getSource().getPlayer();
                                            if (p == null) {
                                                context.getSource().sendFailure(Component.literal("You are not a player."));
                                                return -1;
                                            }
                                            if (p.getMainHandItem().isEmpty()) {
                                                context.getSource().sendFailure(Component.literal("You are not holding an item."));
                                                return 0;
                                            }

                                            Item item = p.getMainHandItem().getItem();

                                            p.sendSystemMessage(
                                                    Component.literal("Item ")
                                                            .append(item.getDescription())
                                                            .append(" has the following item tags:")
                                                            .withStyle(ChatFormatting.GOLD)
                                            );

                                            for (ITag<Item> tag : ForgeRegistries.ITEMS.tags()) {
                                                if (tag.contains(item)) {
                                                    p.sendSystemMessage(Component.literal("   " + tag.getKey().location())
                                                            .withStyle(ChatFormatting.GRAY));
                                                }
                                            }

                                            return 1;
                                        })
                                )
                        )
        );
    }

}