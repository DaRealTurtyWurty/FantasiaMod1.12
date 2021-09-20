package me.ICoding.fanstaia.world.gen.features.trees;

import java.util.Collection;
import java.util.Random;

import net.minecraft.block.BlockLog;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class WorldGenTestTree extends WorldGenAbstractTree
{
	protected IBlockState log;
    protected IBlockState leaves;
	protected IProperty logAxisProperty;
    
    public WorldGenTestTree(float amountPerChunk, IBlockState log, IBlockState leaves) 
    {
    	super(false);
        this.log = log;
        this.leaves = leaves;
        this.logAxisProperty = getAxisProperty(log);
	}

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) 
	{
		//Move down until we reach the ground
		while (position.getY() > 1 && worldIn.isAirBlock(position) || worldIn.getBlockState(position).getBlock().isLeaves(worldIn.getBlockState(position), worldIn, position)) {position = position.down();}
		//Choose a random height
		int height = rand.nextInt(7)+6;
		//Start from the block above the ground
		BlockPos pos = position.up();    
		
		//Generating the trunk using the random height
		for(int y = 0; y < height; ++y)
		{
			this.setLog(worldIn, pos.add(0, y, 0)); 
		}

		this.setLeaves(worldIn, pos.add(0, height, 0));
		BlockPos posLeaf = pos.add(0, height, 0);
		for(int l = 0; l < 250; ++l)
		{
			this.setLeaves(worldIn, posLeaf.add(rand.nextInt(rand.nextInt(4)+5)-3, rand.nextInt(rand.nextInt(4)+5)-3, rand.nextInt(rand.nextInt(4)+5)-3));
		}
		return false;
	}
	
	public boolean setLeaves(World world, BlockPos pos)
    {
		world.setBlockState(pos, this.leaves, 2);
		return true;
    }
    
    public boolean setLog(World world, BlockPos pos)
    {
        return this.setLog(world, pos, null);
    }
    
    public boolean setAir(World world, BlockPos pos)
	{
		world.setBlockToAir(pos);
		return true;
	}
    
	public boolean setLog(World world, BlockPos pos, EnumFacing.Axis axis)
    {
        IBlockState directedLog = (axis != null && this.logAxisProperty != null) ? this.log.withProperty(this.logAxisProperty, BlockLog.EnumAxis.fromFacingAxis(axis)) : this.log;
        world.setBlockState(pos, directedLog, 2);
        return true;
    }
	
	public static IProperty getAxisProperty(IBlockState log)
    {
        for (Object property : log.getProperties().keySet())
        {
            Collection allowedValues = ((IProperty)property).getAllowedValues();
            if (allowedValues.contains(BlockLog.EnumAxis.X) && allowedValues.contains(BlockLog.EnumAxis.Y) && allowedValues.contains(BlockLog.EnumAxis.Z))
            {
                return(IProperty)property;
            }
        }
        return null;
    }
}
