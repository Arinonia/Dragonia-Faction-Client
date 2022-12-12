package fr.arinonia.init;

import fr.arinonia.block.BlockBase;
import net.minecraft.block.Block;

public class BlockRegister {

    public void registerBlocks() {
        int id = 1000;
        this.addBlock(id++, "bauxite_ore", new BlockBase("bauxite_ore"));
        this.addBlock(id++, "bauxite_block", new BlockBase("bauxite_block"));
        this.addBlock(id++, "dolomite_ore", new BlockBase("dolomite_ore"));
        this.addBlock(id++, "dolomite_block", new BlockBase("dolomite_block"));
        this.addBlock(id++, "kobalte_ore", new BlockBase("kobalte_ore"));
        this.addBlock(id++, "kobalte_block", new BlockBase("kobalte_block"));
        this.addBlock(id++, "dragonium_ore", new BlockBase("dragonium_ore"));
        this.addBlock(id++, "dragonium_block", new BlockBase("dragonium_block"));
    }

    private void addBlock(final int id, final String name, final Block block) {
        Block.blockRegistry.addObject(id, name, block);
    }
}
