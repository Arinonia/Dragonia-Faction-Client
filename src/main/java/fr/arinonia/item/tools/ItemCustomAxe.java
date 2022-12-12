package fr.arinonia.item.tools;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemAxe;

public class ItemCustomAxe extends ItemAxe {

    public ItemCustomAxe(ToolMaterial toolMaterial, String name) {
        super(toolMaterial);
        this.setTextureName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(CreativeTabs.tabTools);
    }
}
