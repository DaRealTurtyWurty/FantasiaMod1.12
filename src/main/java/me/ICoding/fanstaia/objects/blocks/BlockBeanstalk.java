package me.ICoding.fanstaia.objects.blocks;

import java.util.Random;

import me.ICoding.fanstaia.init.BlockInit;
import me.ICoding.fanstaia.init.ItemInit;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class BlockBeanstalk extends Block
{
	public BlockBeanstalk(String name) 
	{
		super(Material.PLANTS);
		setUnlocalizedName(name);
		setRegistryName(name);
		setHardness(2.0f);
		setResistance(5.0f);
		setSoundType(SoundType.PLANT);
		 
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
	
	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) 
	{
		return ItemStack.EMPTY;
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) 
	{
		return ItemStack.EMPTY.getItem();
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) 
	{
		return new ItemStack(this);
	}
}
