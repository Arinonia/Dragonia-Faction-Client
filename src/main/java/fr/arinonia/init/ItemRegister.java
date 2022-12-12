package fr.arinonia.init;

import fr.arinonia.item.ItemBase;
import fr.arinonia.item.tools.ItemCustomAxe;
import fr.arinonia.item.tools.ItemCustomHoe;
import fr.arinonia.item.tools.ItemCustomPickaxe;
import fr.arinonia.item.tools.ItemCustomShovel;
import fr.arinonia.item.weapons.ItemCustomSword;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;

public class ItemRegister {

    public void registerItems() {
        int id = 500;
        final ItemBase bauxite = new ItemBase("bauxite");
        final ItemBase dolomite = new ItemBase("dolomite");
        final ItemBase kobalte = new ItemBase("kobalte");
        final ItemBase dragonium = new ItemBase("dragonium");

        this.addItem(id++, "bauxite", bauxite);
        this.addItem(id++, "dolomite", dolomite);
        this.addItem(id++, "kobalte", kobalte);
        this.addItem(id++, "dragonium", dragonium);

        this.addItem(id++, "legendary_dragon_shard", new ItemBase("legendary_dragon_shard"));

        id = this.createToolsSetFromItem(id, bauxite);
        id = this.createToolsSetFromItem(id, dolomite);
        id = this.createToolsSetFromItem(id, kobalte);
        id = this.createToolsSetFromItem(id, dragonium);

        this.addItem(id++, "dragon_sword", new ItemCustomSword(Item.ToolMaterial.DRAGONIUM, "dragon_sword"));

        id = this.createArmorSetFromItem(id, bauxite, 5);
        id = this.createArmorSetFromItem(id, dolomite, 6);
        id = this.createArmorSetFromItem(id, kobalte, 7);
        id = this.createArmorSetFromItem(id, dragonium, 8);


    }

    private void addItem(final int id, final String name, final Item item) {
        Item.itemRegistry.addObject(id, name, item);
    }

    private int createArmorSetFromItem(int id, final ItemBase item, final int renderIndex) {
        final ItemArmor.ArmorMaterial material = ItemArmor.ArmorMaterial.valueOf(item.getName().toUpperCase());
        this.addItem(id++, item.getName() + "_helmer", new ItemArmor(material, renderIndex, 0).setUnlocalizedName(item.getName() + "_helmet").setTextureName(item.getName() + "_helmet"));
        this.addItem(id++, item.getName() + "_chestplate", new ItemArmor(material, renderIndex, 1).setUnlocalizedName(item.getName() + "_chestplate").setTextureName(item.getName() + "_chestplate"));
        this.addItem(id++, item.getName() + "_leggings", new ItemArmor(material, renderIndex, 2).setUnlocalizedName(item.getName() + "_leggings").setTextureName(item.getName() + "_leggings"));
        this.addItem(id++, item.getName() + "_boots", new ItemArmor(material, renderIndex, 3).setUnlocalizedName(item.getName() + "_boots").setTextureName(item.getName() + "_boots"));
        return id;
    }

    private int createToolsSetFromItem(int id, final ItemBase item) {
        final Item.ToolMaterial material = Item.ToolMaterial.valueOf(item.getName().toUpperCase());
        this.addItem(id++, item.getName() + "_axe", new ItemCustomAxe(material, item.getName() + "_axe").setUnlocalizedName(item.getName() + "_axe").setTextureName(item.getName() + "_axe"));
        this.addItem(id++, item.getName() + "_hoe", new ItemCustomHoe(material, item.getName() + "_hoe").setUnlocalizedName(item.getName() + "_hoe").setTextureName(item.getName() + "_hoe"));
        this.addItem(id++, item.getName() + "_pickaxe", new ItemCustomPickaxe(material, item.getName() + "_pickaxe").setUnlocalizedName(item.getName() + "_pickaxe").setTextureName(item.getName() + "_pickaxe"));
        this.addItem(id++, item.getName() + "_shovel", new ItemCustomShovel(material, item.getName() + "_shovel").setUnlocalizedName(item.getName() + "_shovel").setTextureName(item.getName() + "_shovel"));
        this.addItem(id++, item.getName() + "_sword", new ItemCustomSword(material, item.getName() + "_sword").setUnlocalizedName(item.getName() + "_sword").setTextureName(item.getName() + "_sword"));

        return id;
    }
}
