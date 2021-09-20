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

public class WorldGenBush extends WorldGenAbstractTree
{
    protected IBlockState log;
    protected IBlockState leaves;
	protected IProperty logAxisProperty;
    
    public WorldGenBush(float amountPerChunk, IBlockState log, IBlockState leaves) 
    {
    	super(false);
        this.log = log;
        this.leaves = leaves;
        this.logAxisProperty = getAxisProperty(log);
	}
    
	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position)
    {
        // Move down until we reach the ground
        while (position.getY() > 1 && worldIn.isAirBlock(position) || worldIn.getBlockState(position).getBlock().isLeaves(worldIn.getBlockState(position), worldIn, position)) {position = position.down();}
        
        // choose a random height
        int height = rand.nextInt(2)+1;
        
        // start from the block above the ground block
        BlockPos pos = position.up();        

        //Generate a bush 3 blocks tall, with the bottom block already set to a log
        for (int y = 0; y < height; ++y)
        {
            // log in the center
            if (height - y > 1)
            {
                this.setLog(worldIn, pos.add(0, y, 0));
            }  
            
            //Reduces the radius closer to the top of the bush
            int leavesRadius = (height - y > 1 ? 2 : 1);

            for (int x = -leavesRadius; x <= leavesRadius; ++x)
            {
                for (int z = -leavesRadius; z <= leavesRadius; ++z)
                {
                    //Randomly prevent the generation of leaves on the corners of each layer
                    if (Math.abs(x) < leavesRadius || Math.abs(z) < leavesRadius || rand.nextInt(2) != 0)
                    {
	                    if (rand.nextInt(3) == 0)
	                    {
	                    	this.setLeaves(worldIn, pos.add(x, y, z));
	                    	if (height - y > 1)
	                        {
	                            this.setLog(worldIn, pos.add(0, y, 0));
	                        } 
	                    }
	                    
                    	else
                    	{
                    		this.setLeaves(worldIn, pos.add(x, y, z));
                    		if (height - y > 1)
                            {
                                this.setLog(worldIn, pos.add(0, y, 0));
                            } 
                    	}
                    }
                }
            }          
        }

        return true;
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
