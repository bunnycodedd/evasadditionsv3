package dev.bunnycodedd.eadditions.block;

import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.util.entry.RegistryEntry;
import dev.bunnycodedd.eadditions.EAdditions;
import net.minecraft.world.level.block.Block;

public class ModBlocks {

    private static final CreateRegistrate REGISTRATE = EAdditions.registrate();

    public static final RegistryEntry<Block> TEST_BLOCK = REGISTRATE.block("test_block", Block::new)
            .register();

    public static void register() {}
}