package net.minecraft.world.biome;

import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.init.Blocks;

public class BiomeGenEnd extends BiomeGenBase
{
    private static final String __OBFID = "CL_00000187";

    public BiomeGenEnd(int p_i1990_1_)
    {
        super(p_i1990_1_);
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableMonsterList.add(new SpawnListEntry(EntityEnderman.class, 10, 4, 4));
        this.topBlock = Blocks.dirt;
        this.fillerBlock = Blocks.dirt;
        this.theBiomeDecorator = new BiomeEndDecorator();
    }

    /**
     * takes temperature, returns color
     */
    public int getSkyColorByTemp(float p_76731_1_)
    {
        return 0;
    }
}
