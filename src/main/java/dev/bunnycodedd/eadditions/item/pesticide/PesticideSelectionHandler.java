package dev.bunnycodedd.eadditions.item.pesticide;

import com.simibubi.create.foundation.utility.CreateLang;
import com.simibubi.create.foundation.utility.RaycastHelper;
import net.createmod.catnip.outliner.Outliner;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;

import java.util.Set;

public class PesticideSelectionHandler {

    private static final int PASSIVE = 0x4D9162;
    private static final int HIGHLIGHT = 0x68c586;
    private static final int FAIL = 0xc5b548;

    private Object clusterOutlineSlot = new Object();
    private Object bbOutlineSlot = new Object();
    private int clusterCooldown;

    private BlockPos firstPos;
    private BlockPos hoveredPos;
    private Set<BlockPos> currentCluster;
    private int pesticideRequired;

    public void tick() {
        Minecraft mc = Minecraft.getInstance();
        LocalPlayer player = mc.player;
        BlockPos hovered = null;
        ItemStack stack = player.getMainHandItem();

        if (!isValidItem(stack)) {
            if (firstPos != null)
                discard();
            return;
        }

        if (clusterCooldown > 0) {
            if (clusterCooldown == 25)
                player.displayClientMessage(CommonComponents.EMPTY, true);
            Outliner.getInstance().keep(clusterOutlineSlot);
            clusterCooldown--;
        }
        AABB scanArea = player.getBoundingBox()
                .inflate(1, 2, 1);

        if (firstPos == null) {
            double range = player.getAttribute(ForgeMod.BLOCK_REACH.get())
                    .getValue() + 1;
            Vec3 traceOrigin = player.getEyePosition();
            Vec3 traceTarget = RaycastHelper.getTraceTarget(player, range, traceOrigin);

            double bestDistance = Double.MAX_VALUE;
            Outliner.getInstance().showAABB(bbOutlineSlot, scanArea);
        }
    }

    public void discard() {
        LocalPlayer player = Minecraft.getInstance().player;
        currentCluster = null;
        firstPos = null;
        CreateLang.translate("pesticide.abort")
                .sendStatus(player);
        clusterCooldown = 0;
    }

    public boolean isValidItem(ItemStack item) {
        return item.getItem() instanceof PesticideItem;
    }
}