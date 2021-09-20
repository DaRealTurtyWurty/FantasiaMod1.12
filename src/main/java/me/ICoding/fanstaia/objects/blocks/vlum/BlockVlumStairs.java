package me.ICoding.fanstaia.objects.blocks.vlum;

import me.ICoding.fanstaia.Main;
import me.ICoding.fanstaia.init.BlockInit;
import me.ICoding.fanstaia.init.ItemInit;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;

public class BlockVlumStairs extends BlockStairs
{
	public BlockVlumStairs(String name, IBlockState modelState) 
	{
		super(modelState);	
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.MAIN_TAB);
		setHardness(2.0f);
		setSoundType(SoundType.WOOD);
		
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
}
