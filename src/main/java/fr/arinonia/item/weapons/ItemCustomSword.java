package fr.arinonia.item.weapons;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSword;

public class ItemCustomSword extends ItemSword {

    public ItemCustomSword(ToolMaterial toolMaterial, String name) {
        super(toolMaterial);
        this.setTextureName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(CreativeTabs.tabCombat);
    }
}
