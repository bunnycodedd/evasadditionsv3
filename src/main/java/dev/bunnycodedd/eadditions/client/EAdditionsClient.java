package dev.bunnycodedd.eadditions.client;

import dev.bunnycodedd.eadditions.EAdditions;
import dev.bunnycodedd.eadditions.item.pesticide.PesticideSelectionHandler;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EAdditions.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class EAdditionsClient {

    public static final PesticideSelectionHandler PESTICIDE_HANDLER = new PesticideSelectionHandler();

    @SubscribeEvent
    public static void onTick(TickEvent.ClientTickEvent event) {
        if (!isGameActive())
            return;

        PESTICIDE_HANDLER.tick();
    }

    protected static boolean isGameActive() {
        return !(Minecraft.getInstance().level == null || Minecraft.getInstance().player == null);
    }
}