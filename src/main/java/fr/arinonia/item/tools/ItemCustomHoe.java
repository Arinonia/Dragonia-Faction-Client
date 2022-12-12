package fr.arinonia.item.tools;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemHoe;

public class ItemCustomHoe extends ItemHoe {

    public ItemCustomHoe(ToolMaterial toolMaterial, String name) {
        super(toolMaterial);
        this.setTextureName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(CreativeTabs.tabTools);
    }
}
