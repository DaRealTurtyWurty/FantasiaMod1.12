package me.ICoding.fanstaia.world.gen.biomes;

import java.util.Random;

import me.ICoding.fanstaia.init.BlockInit;
import me.ICoding.fanstaia.world.gen.features.WorldGenMysticMushroom;
import me.ICoding.fanstaia.world.gen.features.trees.WorldGenVlumTree;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class VlumWoods extends Biome 
{
	public VlumWoods() 
	{
		super(new BiomeProperties("Vlum Woods")
				.setBaseHeight(0.2f)
				.setHeightVariation(0.035f)
				.setTemperature(0.25f)
				.setWaterColor(16724639));

		this.topBlock = Blocks.GRASS.getDefaultState();
		this.fillerBlock = Blocks.DIRT.getDefaultState();
		this.decorator.treesPerChunk = 5;
		this.decorator.extraTreeChance = 0.7f;
	}

	@Override
	public int getSkyColorByTemp(float currentTemperature) 
	{
		return 14297820;
	}

	@Override
	public int getModdedBiomeGrassColor(int original) 
	{
		return 55496;
	}
	
	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand) 
	{
		if(rand.nextInt(20) > 5)
		{
			return new WorldGenVlumTree();
		}
		return new WorldGenMysticMushroom(BlockInit.MYSTIC_MUSHROOM_BLOCK);
	}
}
