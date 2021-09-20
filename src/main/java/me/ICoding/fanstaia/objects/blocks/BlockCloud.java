package me.ICoding.fanstaia.objects.blocks;

import me.ICoding.fanstaia.Main;
import me.ICoding.fanstaia.init.BlockInit;
import me.ICoding.fanstaia.init.ItemInit;
import me.ICoding.fanstaia.util.CustomMaterial;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockRenderLayer;

public class BlockCloud extends Block
{
	public BlockCloud(String name) 
	{
		super(CustomMaterial.CLOUD);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.MAIN_TAB);
		setSoundType(SoundType.SLIME);
		setHardness(0.1f);
		setResistance(1.0f);
		setLightOpacity(1);
		
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) 
	{
		return false;
	}
	
	@Override
	public BlockRenderLayer getBlockLayer() 
	{
		return BlockRenderLayer.TRANSLUCENT;
	}
}