package me.ICoding.fanstaia.objects.blocks.tree_structureblock;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TileEntityTreeStructureBlock extends TileEntity implements ITickable
{
	public int blocks = 0;
	//TODO: Fix Wool checking
	
	/*@Override
	public void update() 
	{
		World worldIn = this.getWorld();
		BlockPos pos = this.getPos();
		
		BlockPos[] woolLocations = new BlockPos[] {pos.up(1), pos.down(1), pos.down(2), pos.down(3), pos.down(4), pos.east(1), pos.east(1).down(1), pos.east(1).up(1), pos.east(1).down(1).north(1),
				pos.east(1).down(1).south(1), pos.west(1), pos.west(1).down(1), pos.west(1).up(1), pos.west(1).down(1).north(1), pos.west(1).down(1).south(1), pos.north(1), pos.north(1).down(1), 
				pos.north(1).up(1), pos.south(1), pos.south(1).down(1), pos.south(1).up(1)};
		
		HashMap<BlockPos, IBlockState> storedBlocks = new HashMap<BlockPos, IBlockState>();
		
		for(BlockPos position : woolLocations)
		{
			if(isWool(worldIn.getBlockState(position).getBlock()))
			{
				blocks++;
				storedBlocks.put(position, worldIn.getBlockState(position));
			}
			
			if(worldIn.getBlockState(position) != storedBlocks.get(position))
			{
				storedBlocks.remove(position);
				blocks--;
			}
			else
			{
				
			}
		}
		
		if(blocks == 16)
		{
			System.out.println("Structure Completed!");
		}
		
		if(worldIn.getBlockState(pos).getBlock() != BlockInit.TREE_STRUCTURE_BLOCK)
		{
			storedBlocks.clear();
		}
		System.out.println(blocks);
	}*/
	
	@SuppressWarnings("unused")
	private static boolean isWool(Block block)
	{
		if(block.equals(Blocks.WOOL))
		{
			return true;
		}
		return false;
	}

	@Override
	public void update() 
	{
		
	}
}
