package fr.arinonia.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item {

    private final String name;

    public ItemBase(String name) {
        this.name = name;
        this.setTextureName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(CreativeTabs.tabMaterials);
    }

    public String getName() {
        return this.name;
    }
}
