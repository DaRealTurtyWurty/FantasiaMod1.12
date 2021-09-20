package me.ICoding.fanstaia.objects.blocks.cherry;

import me.ICoding.fanstaia.Main;
import me.ICoding.fanstaia.init.BlockInit;
import me.ICoding.fanstaia.init.ItemInit;
import net.minecraft.block.BlockPressurePlate;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;

public class BlockCherryPressurePlate extends BlockPressurePlate
{
	public BlockCherryPressurePlate(String name) 
	{
		super(Material.WOOD, Sensitivity.EVERYTHING);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.MAIN_TAB);
		setHardness(1.2f);
		setSoundType(SoundType.WOOD);
		
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
}
