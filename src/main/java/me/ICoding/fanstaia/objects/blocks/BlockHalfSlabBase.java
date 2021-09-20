package me.ICoding.fanstaia.objects.blocks;

import me.ICoding.fanstaia.init.ItemInit;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSlab;

public class BlockHalfSlabBase extends BlockSlabBase
{
	public BlockHalfSlabBase(String name, Material materialIn, CreativeTabs tab, BlockSlab half, BlockSlab doubleSlab)
	{
		super(name, materialIn, half);
		setCreativeTab(tab);
		ItemInit.ITEMS.add(new ItemSlab(this, this, doubleSlab).setRegistryName(name));
	}
	
	@Override
	public boolean isDouble() 
	{
		return false;
	}
}
