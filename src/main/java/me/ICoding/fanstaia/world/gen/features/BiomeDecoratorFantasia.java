package me.ICoding.fanstaia.world.gen.features;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.gen.ChunkGeneratorSettings;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeDecoratorFantasia extends BiomeDecorator
{
	public WorldGenerator worldGenBeanstalk = new WorldGenBeanstalk();
	public BlockPos chunkPos;

	protected void genDecorations(Biome biomeIn, World worldIn, Random random) 
	{
		net.minecraft.util.math.ChunkPos forgeChunkPos = new net.minecraft.util.math.ChunkPos(chunkPos); // actual ChunkPos instead of BlockPos, used for events
		net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.event.terraingen.DecorateBiomeEvent.Pre(worldIn, random, forgeChunkPos));

		if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, random, forgeChunkPos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.CUSTOM))
		{
			if(random.nextInt(1000) == 327)
			{
				int k6 = random.nextInt(16) + 8;
	            int l = random.nextInt(16) + 8;
				BlockPos blockpos = worldIn.getHeight(this.chunkPos.add(k6, 0, l));
				worldGenBeanstalk.generate(worldIn, random, blockpos);
			}
		}
		
		int k1 = random.nextInt(1);

        if (random.nextFloat() < 0.5f)
        {
            ++k1;
        }
		
		if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, random, forgeChunkPos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.TREE))
		{
	        for (int j2 = 0; j2 < k1; ++j2)
	        {
	            int k6 = random.nextInt(16) + 8;
	            int l = random.nextInt(16) + 8;
	            WorldGenAbstractTree worldgenabstracttree = biomeIn.getRandomTreeFeature(random);
	            worldgenabstracttree.setDecorationDefaults();
	            BlockPos blockpos = worldIn.getHeight(this.chunkPos.add(k6, 0, l));

	            if (worldgenabstracttree.generate(worldIn, random, blockpos))
	            {
	                worldgenabstracttree.generateSaplings(worldIn, random, blockpos);
	            }
	        }
		}
		
		if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, random, forgeChunkPos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS))
		{
	        for (int i3 = 0; i3 < random.nextInt(1) + 2; ++i3)
	        {
	            int j7 = random.nextInt(16) + 8;
	            int i11 = random.nextInt(16) + 8;
	            int k14 = worldIn.getHeight(this.chunkPos.add(j7, 0, i11)).getY() * 2;

	            if (k14 > 0)
	            {
	                int l17 = random.nextInt(k14);
	                biomeIn.getRandomWorldGenForGrass(random).generate(worldIn, random, this.chunkPos.add(j7, l17, i11));
	            }
	        }
		}
	}
	
	@Override
	public void decorate(World worldIn, Random random, Biome biome, BlockPos pos) 
	{
		if (this.decorating)
        {
            throw new RuntimeException("Already decorating");
        }
        else
        {
            this.chunkProviderSettings = ChunkGeneratorSettings.Factory.jsonToFactory(worldIn.getWorldInfo().getGeneratorOptions()).build();
            this.chunkPos = pos;
            this.genDecorations(biome, worldIn, random);
            this.decorating = false;
        }
	}
}
