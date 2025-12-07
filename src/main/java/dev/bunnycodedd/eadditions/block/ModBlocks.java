package dev.bunnycodedd.eadditions.block;

import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.item.ItemDescription;
import com.tterrag.registrate.util.DataIngredient;
import com.tterrag.registrate.util.entry.RegistryEntry;
import dev.bunnycodedd.eadditions.EAdditions;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.block.SlabBlock;

public class ModBlocks {

    private static final CreateRegistrate REGISTRATE = EAdditions.registrate();

    public static final RegistryEntry<SlabBlock> GRASS_SLAB = REGISTRATE.block("grass_slab", SlabBlock::new)
            .blockstate((ctx, provider) -> {
                provider.slabBlock(
                        ctx.get(),
                        ResourceLocation.withDefaultNamespace("block/moss_block"),
                        ResourceLocation.withDefaultNamespace("block/moss_block")
                        );
            })
            .color(() -> () -> (state, world, pos, layer) -> GrassColor.getDefaultColor())
            .simpleItem()
            .recipe((ctx, provider) -> {
                provider.slab(DataIngredient.items(Items.GRASS_BLOCK, Items.MOSS_BLOCK), RecipeCategory.BUILDING_BLOCKS, ctx, null, false);
            })
            .register();

    public static void register() {}
}