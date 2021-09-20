package me.ICoding.fanstaia.objects.blocks;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockDoubleSlabBase extends BlockSlabBase
{
	public BlockDoubleSlabBase(String name, Material materialIn, CreativeTabs tab, BlockSlab half) 
	{
		super(name, materialIn, half);
		setCreativeTab(tab);
	}

	@Override
	public boolean isDouble() 
	{	
		return true;
	}
}
