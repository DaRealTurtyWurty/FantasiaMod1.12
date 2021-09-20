package me.ICoding.fanstaia.objects.blocks.cherry;

import me.ICoding.fanstaia.Main;
import me.ICoding.fanstaia.init.BlockInit;
import me.ICoding.fanstaia.init.ItemInit;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockCherryBark extends Block
{
	public BlockCherryBark(String name) 
	{
		super(Material.WOOD);
		setHardness(2.5f);
		setSoundType(SoundType.WOOD);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.MAIN_TAB);
		
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
	
	@Override
	public boolean isWood(IBlockAccess world, BlockPos pos) 
	{
		return true;
	}
	
	@Override
	public boolean canSustainLeaves(IBlockState state, IBlockAccess world, BlockPos pos) 
	{
		return true;
	}
}
