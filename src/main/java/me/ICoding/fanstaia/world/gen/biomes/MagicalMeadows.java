package me.ICoding.fanstaia.world.gen.biomes;

import java.util.Random;

import me.ICoding.fanstaia.init.BlockInit;
import me.ICoding.fanstaia.world.gen.features.BiomeDecoratorFantasia;
import me.ICoding.fanstaia.world.gen.features.trees.WorldGenTestTree;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class MagicalMeadows extends Biome
{
	public MagicalMeadows()
    {
		super(new BiomeProperties("Magical Meadows")
				.setBaseHeight(0.2f)
				.setHeightVariation(0.02f)
				.setTemperature(0.5f)
				.setWaterColor(16724639));
		
        this.spawnableCaveCreatureList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();
        
        decorator.treesPerChunk = 0;
        decorator.extraTreeChance = 0.05F;
        decorator.flowersPerChunk = 4;
        decorator.grassPerChunk = -999;
        decorator = new BiomeDecoratorFantasia();
    }
    
    @Override
	public int getSkyColorByTemp(float currentTemperature) 
	{
		return 14297820;
	}

	@Override
	public int getModdedBiomeGrassColor(int original) 
	{
		return 65432;
	}
	
	@Override
	public int getModdedBiomeFoliageColor(int original) 
	{
		return 65432;
	}

    @Override
    public void addDefaultFlowers()
    {
        this.addFlower(BlockInit.NEPETA_FLOWER.getDefaultState(), 30);
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand)
    {
        //return new WorldGenBush(rand.nextInt(3)+1, BlockInit.VUZINE_LOG.getDefaultState(), BlockInit.VUZINE_LEAVES.getDefaultState());
    	return new WorldGenTestTree(rand.nextInt(1), BlockInit.VUZINE_LOG.getDefaultState(), BlockInit.VUZINE_LEAVES.getDefaultState());
    }
}
