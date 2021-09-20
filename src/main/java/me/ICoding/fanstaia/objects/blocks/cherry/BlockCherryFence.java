package me.ICoding.fanstaia.objects.blocks.cherry;

import me.ICoding.fanstaia.Main;
import me.ICoding.fanstaia.init.BlockInit;
import me.ICoding.fanstaia.init.ItemInit;
import net.minecraft.block.BlockFence;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;

public class BlockCherryFence extends BlockFence
{
	public BlockCherryFence(String name) 
	{
		super(Material.WOOD, MapColor.RED_STAINED_HARDENED_CLAY);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.MAIN_TAB);
		setSoundType(SoundType.WOOD);
		setHardness(1.2f);
		
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
}
