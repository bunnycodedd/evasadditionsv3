package dev.bunnycodedd.eadditions.block;

import com.simibubi.create.foundation.data.AssetLookup;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.SharedProperties;
import com.simibubi.create.foundation.data.TagGen;
import com.tterrag.registrate.util.DataIngredient;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.entry.RegistryEntry;
import dev.bunnycodedd.eadditions.EAdditions;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.block.SlabBlock;

import static com.simibubi.create.foundation.data.BlockStateGen.*;
import static com.simibubi.create.foundation.data.ModelGen.customItemModel;

public class ModBlocks {

    private static final CreateRegistrate REGISTRATE = EAdditions.registrate();

    public static final BlockEntry<MechanicalComposterBlock> MECHANICAL_COMPOSTER = REGISTRATE
            .block("mechanical_composter", MechanicalComposterBlock::new)
            .initialProperties(SharedProperties::wooden)
            .transform(TagGen.axeOrPickaxe())
            .blockstate((c, p) ->
                    p.horizontalBlock(c.get(), p.models()
                            .getExistingFile(p.modLoc("block/mechanical_composter/block"))))
            .item()
            .transform(customItemModel("mechanical_composter/block"))
            .register();

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


    public static void register() {
    }
}