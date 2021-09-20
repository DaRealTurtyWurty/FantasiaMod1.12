package me.ICoding.fanstaia.world.gen.biomes;

import java.util.Random;

import me.ICoding.fanstaia.init.BlockInit;
import me.ICoding.fanstaia.world.gen.features.trees.WorldGenCherryTree;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;	

public class CherryGrove extends Biome 
{
	public CherryGrove() 
	{
		super(new BiomeProperties("Cherry Grove")
				.setBaseHeight(0.2f)
				.setHeightVariation(0.03f)
				.setTemperature(1.0f)
				.setWaterColor(16724639));

		this.topBlock = Blocks.GRASS.getDefaultState();
		this.fillerBlock = Blocks.DIRT.getDefaultState();
		this.decorator.treesPerChunk = 8;
		this.decorator.extraTreeChance = 0.025f;
	}

	@Override
	public int getSkyColorByTemp(float currentTemperature) 
	{
		return 14297820;
	}

	@Override
	public int getModdedBiomeGrassColor(int original) 
	{
		return 55452;
	}
	
	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand) 
	{
		return new WorldGenCherryTree(rand.nextInt(2) == 0 ? BlockInit.WHITE_CHERRY_LEAVES.getDefaultState() : BlockInit.PINK_CHERRY_LEAVES.getDefaultState());
	}
}
