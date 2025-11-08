package dev.bunnycodedd.eadditions;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Forge's config APIs
@Mod.EventBusSubscriber(modid = EAdditions.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec.IntValue MAGIC_NUMBER = BUILDER.comment("A magic number").defineInRange("magicNumber", 42, 0, Integer.MAX_VALUE);
    public static final ForgeConfigSpec.BooleanValue DISABLE_SURFACE_SPAWNING = BUILDER.comment("Disables spawning of hostile Mobs during nighttime at the surface").define("disableSurfaceSpawning", false);

    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static int magicNumber;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        magicNumber = MAGIC_NUMBER.get();
        // convert the list of strings into a set of items
    }
}
