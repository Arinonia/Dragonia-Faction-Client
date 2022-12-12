package fr.arinonia.item.tools;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSpade;

public class ItemCustomShovel extends ItemSpade {

    public ItemCustomShovel(ToolMaterial toolMaterial, String name) {
        super(toolMaterial);
        this.setTextureName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(CreativeTabs.tabTools);
    }
}
