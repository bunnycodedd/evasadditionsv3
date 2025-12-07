package dev.bunnycodedd.eadditions.item;

import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.RegistryEntry;
import dev.bunnycodedd.eadditions.EAdditions;
import dev.bunnycodedd.eadditions.item.pesticide.PesticideItem;

public class ModItems {

    private static final CreateRegistrate REGISTRATE = EAdditions.registrate();

    public static final RegistryEntry<PesticideItem> PESTICIDE = REGISTRATE.item("pesticide", PesticideItem::new)
            .properties(properties -> properties.stacksTo(1).durability(99))
            .defaultModel()
            .register();

    public static void register() {}

}