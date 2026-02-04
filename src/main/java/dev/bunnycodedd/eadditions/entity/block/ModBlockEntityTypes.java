package dev.bunnycodedd.eadditions.entity.block;

import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.OrientedRotatingVisual;
import com.simibubi.create.content.kinetics.base.ShaftVisual;
import com.simibubi.create.content.kinetics.base.SingleAxisRotatingVisual;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import dev.bunnycodedd.eadditions.EAdditions;
import dev.bunnycodedd.eadditions.block.ModBlocks;
import dev.bunnycodedd.eadditions.entity.block.composter.MechanicalComposterBlockEntity;
import dev.bunnycodedd.eadditions.entity.block.composter.MechanicalComposterRenderer;

public class ModBlockEntityTypes {

    private static final CreateRegistrate REGISTRATE = EAdditions.registrate();

    public static final BlockEntityEntry<MechanicalComposterBlockEntity> MECHANICAL_COMPOSTER = REGISTRATE
            .blockEntity("mechanical_composter", MechanicalComposterBlockEntity::new)
            .visual(() -> ShaftVisual::shaft, true)
            .validBlock(ModBlocks.MECHANICAL_COMPOSTER)
            .renderer(() -> MechanicalComposterRenderer::new)
            .register();

    public static void register() {
    }

}