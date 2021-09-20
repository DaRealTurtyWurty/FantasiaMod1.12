package me.ICoding.fanstaia.objects.blocks.mystic_mushroom;

import me.ICoding.fanstaia.Main;
import me.ICoding.fanstaia.init.BlockInit;
import me.ICoding.fanstaia.init.ItemInit;
import net.minecraft.block.BlockHugeMushroom;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;

public class MysticMushroomBlock extends BlockHugeMushroom
{
	public MysticMushroomBlock(String name) 
	{
		super(Material.PLANTS, MapColor.BLUE, BlockInit.MYSTIC_MUSHROOM);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.MAIN_TAB);
		
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
}