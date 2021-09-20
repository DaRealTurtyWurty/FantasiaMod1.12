package me.ICoding.fanstaia.objects.blocks.cherry;

import me.ICoding.fanstaia.Main;
import me.ICoding.fanstaia.init.BlockInit;
import me.ICoding.fanstaia.init.ItemInit;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;

public class BlockCherryPlanks extends Block
{
	public BlockCherryPlanks(String name) 
	{
		super(Material.WOOD);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.MAIN_TAB);
		setHardness(2.0f);
		setSoundType(SoundType.WOOD);
		setHarvestLevel("axe", -1);
		
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
}
