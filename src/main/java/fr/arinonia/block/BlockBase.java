package fr.arinonia.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockBase extends Block {

    public BlockBase(final String name) {
        super(Material.rock);
        this.setBlockName(name);
        this.setBlockTextureName(name);
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setResistance(5.0F);
        this.setHardness(3.0F);
        this.setStepSound(Block.soundTypeStone);
    }
}
