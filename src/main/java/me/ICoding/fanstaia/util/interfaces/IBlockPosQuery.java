package me.ICoding.fanstaia.util.interfaces;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IBlockPosQuery 
{
	boolean matches(World world, BlockPos pos);
}
