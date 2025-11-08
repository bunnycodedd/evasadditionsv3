package dev.bunnycodedd.eadditions;

import com.mojang.logging.LogUtils;
import com.simibubi.create.foundation.data.CreateRegistrate;
import dev.bunnycodedd.eadditions.block.ModBlocks;
import dev.bunnycodedd.eadditions.command.ModCommands;
import dev.bunnycodedd.eadditions.entity.EntitySpawnListener;
import net.minecraft.client.Minecraft;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(EAdditions.MODID)
public class EAdditions {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "eadditions";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MODID);


    public EAdditions(FMLJavaModLoadingContext ctx) {
        IEventBus modEventBus = ctx.getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.addListener(EntitySpawnListener::onEntitySpawning);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        ModBlocks.register();

        REGISTRATE.registerEventListeners(modEventBus);
        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ctx.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
    @SubscribeEvent
    public void registerCommands(RegisterCommandsEvent event) {
        ModCommands.register(event.getDispatcher());
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");
        LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));

        LOGGER.info("Magic Number: {}", Config.magicNumber);

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }

    public static CreateRegistrate registrate() {
        return REGISTRATE;
    }
}
