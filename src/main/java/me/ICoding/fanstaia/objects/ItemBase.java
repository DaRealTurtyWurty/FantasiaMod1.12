package me.ICoding.fanstaia.objects;

import me.ICoding.fanstaia.Main;
import me.ICoding.fanstaia.init.ItemInit;
import net.minecraft.item.Item;

public class ItemBase extends Item 
{
	public ItemBase(String name) 
	{
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.MAIN_TAB);
		
		ItemInit.ITEMS.add(this);
	}
}
