package net.mehvahdjukaar.every_compat.modules.forge.builders_delight;

import com.tynoxs.buildersdelight.content.item.BdItem;

public class CustomBdFurnitureKit extends BdItem {
    private final String woodType;

    public CustomBdFurnitureKit(String wood, Properties properties) {
        super(properties);
        this.woodType = wood;
    }

}
