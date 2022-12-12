package fr.arinonia.item.tools;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemPickaxe;

public class ItemCustomPickaxe extends ItemPickaxe {

    public ItemCustomPickaxe(ToolMaterial toolMaterial, String name) {
        super(toolMaterial);
        this.setTextureName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(CreativeTabs.tabTools);
    }
}
