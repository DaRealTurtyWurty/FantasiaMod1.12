package me.ICoding.fanstaia.objects.blocks.tree_structureblock;

import me.ICoding.fanstaia.Main;
import me.ICoding.fanstaia.init.BlockInit;
import me.ICoding.fanstaia.init.ItemInit;
import me.ICoding.fanstaia.util.DimensionTeleporter;
import me.ICoding.fanstaia.util.handlers.ConfigHandler;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockTreeStructure extends Block implements ITileEntityProvider
{
	public BlockTreeStructure(String name) 
	{
		super(Material.CLOTH);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.MAIN_TAB);
		setHardness(0.2f);
		setResistance(5.0f);
		setSoundType(SoundType.CLOTH);
		
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) 
	{
		if(!worldIn.isRemote)
		{
			DimensionTeleporter.teleportToDimension(playerIn, ConfigHandler.FANTASIA_DIM, pos.getX(), pos.getY(), pos.getZ());
			return true;
		}
		return false;
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state) 
	{
		return true;
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) 
	{
		return new TileEntityTreeStructureBlock();
	}
}
