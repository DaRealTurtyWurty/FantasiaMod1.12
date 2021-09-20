package me.ICoding.fanstaia.world.gen.features;

import java.util.Random;

import me.ICoding.fanstaia.init.BlockInit;
import me.ICoding.fanstaia.worlddata.StructureSpawning;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGenBeanstalk extends WorldGenerator implements IWorldGenerator
{
	NBTTagCompound nbt = new NBTTagCompound();
	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) 
	{
		int topY = rand.nextInt(25)+15;
        for(int y = 0; y < topY; y++)
        {
        	worldIn.setBlockToAir(position);
        	worldIn.setBlockState(position, BlockInit.BEANSTALK_BLOCK.getDefaultState());
        	worldIn.setBlockState(position.up(y), BlockInit.BEANSTALK_BLOCK.getDefaultState());
        	worldIn.setBlockState(position.up(topY), BlockInit.GOLDEN_EGG.getDefaultState());
        	if(rand.nextInt(3) == 0)
        	{
        		if(rand.nextInt(3) == 0)
        		{
        			worldIn.setBlockState(position.up(y).north(), BlockInit.BEANSTALK_BLOCK.getDefaultState());
        		}
        		else if(rand.nextInt(3) == 1)
        		{
        			worldIn.setBlockState(position.up(y).south(), BlockInit.BEANSTALK_BLOCK.getDefaultState());
        		}
        		else if(rand.nextInt(3) == 2)
        		{
        			worldIn.setBlockState(position.up(y).east(), BlockInit.BEANSTALK_BLOCK.getDefaultState());
        		}
        		else
        		{
        			if(rand.nextInt(1) == 0)
        			{
        				worldIn.setBlockState(position.up(y).west(), BlockInit.BEANSTALK_BLOCK.getDefaultState());
        			}
        		}
        	}
        }
        return true;
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) 
	{
		StructureSpawning data = StructureSpawning.get(world);
		int deltaY = 255;
		BlockPos position = new BlockPos((chunkX*16) + random.nextInt(16), 0 + random.nextInt(deltaY), (chunkZ*16) + random.nextInt(16));
		int topY = random.nextInt(25)+15;
		if(world.getBiome(position) == Biomes.PLAINS && position.getZ() > 7000 && data.getBeanstalkCount() < 1 && world.provider.getDimension() == 0 
				&& world.getBlockState(position.down()).getBlock() == Blocks.GRASS || position.getX() > 7000 && world.getBiome(position) == Biomes.PLAINS 
				&& data.getBeanstalkCount() < 1 && world.provider.getDimension() == 0 && world.getBlockState(position.down()).getBlock() == Blocks.GRASS 
				|| position.getZ() < -7000 && world.getBiome(position) == Biomes.PLAINS && data.getBeanstalkCount() < 1 && world.provider.getDimension() == 0 
				&& world.getBlockState(position.down()).getBlock() == Blocks.GRASS || position.getX() < -7000 && data.getBeanstalkCount() < 1 && world.provider.getDimension() == 0 
				&& world.getBiome(position) == Biomes.PLAINS && world.getBlockState(position.down()).getBlock() == Blocks.GRASS)
		{
			for(int y = 0; y < topY; y++)
	        {
	        	world.setBlockToAir(position);
	        	world.setBlockState(position, BlockInit.BEANSTALK_BLOCK.getDefaultState());
	        	world.setBlockState(position.up(y), BlockInit.BEANSTALK_BLOCK.getDefaultState());
	        	world.setBlockState(position.up(topY), BlockInit.GOLDEN_EGG.getDefaultState());
	        	if(random.nextInt(3) == 0)
	        	{
	        		data.setBeanstalkCount(data.getBeanstalkCount() + 1);
	        		if(random.nextInt(3) == 0)
	        		{
	        			world.setBlockState(position.up(y).north(), BlockInit.BEANSTALK_BLOCK.getDefaultState());
	        		}
	        		else if(random.nextInt(3) == 1)
	        		{
	        			world.setBlockState(position.up(y).south(), BlockInit.BEANSTALK_BLOCK.getDefaultState());
	        		}
	        		else if(random.nextInt(3) == 2)
	        		{
	        			world.setBlockState(position.up(y).east(), BlockInit.BEANSTALK_BLOCK.getDefaultState());
	        		}
	        		else
	        		{
	        			if(random.nextInt(1) == 0)
	        			{
	        				world.setBlockState(position.up(y).west(), BlockInit.BEANSTALK_BLOCK.getDefaultState());
	        			}
	        		}
	        	}
	        }
		}
	}
}
