package me.ICoding.fanstaia.objects.blocks.vlum;

import me.ICoding.fanstaia.Main;
import me.ICoding.fanstaia.init.BlockInit;
import me.ICoding.fanstaia.init.ItemInit;
import net.minecraft.block.BlockLadder;
import net.minecraft.block.SoundType;
import net.minecraft.item.ItemBlock;

public class BlockVlumLadder extends BlockLadder
{
	public BlockVlumLadder(String name) 
	{
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.MAIN_TAB);
		setHardness(1.2f);
		setSoundType(SoundType.WOOD);
		
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
}
