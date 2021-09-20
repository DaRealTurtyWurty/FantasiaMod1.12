package me.ICoding.fanstaia.objects.blocks;

import me.ICoding.fanstaia.Main;
import me.ICoding.fanstaia.init.BlockInit;
import me.ICoding.fanstaia.init.ItemInit;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockGoldenEgg extends Block
{
	public static final AxisAlignedBB GOLDEN_EGG_AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 1.0D, 0.9375D);
	public BlockGoldenEgg(String name) 
	{
		super(Material.GROUND);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.MAIN_TAB);
		setHardness(5.0f);
		setResistance(30.0f);
		setSoundType(SoundType.STONE);
		setHarvestLevel("pickaxe", 2);
		
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) 
	{
		return GOLDEN_EGG_AABB;
	}
	
	@Override
	public boolean isFullBlock(IBlockState state) 
	{
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState state) 
	{
		return false;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) 
	{
		return false;
	}
	
	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) 
	{
		if(worldIn.getBlockState(pos.down()).getBlock() == Blocks.AIR)
		{
			if(!worldIn.isRemote)
			{
				EntityFallingBlock entityfallingblock = new EntityFallingBlock(worldIn, (double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, worldIn.getBlockState(pos));
				worldIn.spawnEntity(entityfallingblock);
			}
		}
	}
	
	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) 
	{
		if(worldIn.getBlockState(pos.down()).getBlock() != Blocks.AIR)
		{
			return true;
		}
		return false;
	}
	
	@Override
	public boolean canPlaceTorchOnTop(IBlockState state, IBlockAccess world, BlockPos pos) 
	{
		return true;
	}
}
