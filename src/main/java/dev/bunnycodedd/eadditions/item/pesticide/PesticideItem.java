package dev.bunnycodedd.eadditions.item.pesticide;

import net.minecraft.world.item.Item;

public class PesticideItem extends Item {

    public PesticideItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean canBeDepleted() {
        return true;
    }

}