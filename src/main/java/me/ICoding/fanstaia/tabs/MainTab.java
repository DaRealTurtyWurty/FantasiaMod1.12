package me.ICoding.fanstaia.tabs;

import me.ICoding.fanstaia.init.ItemInit;
import me.ICoding.fanstaia.util.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MainTab extends CreativeTabs 
{
	public MainTab(String name) 
	{
		super(name);
	}
	
	@Override
	public ResourceLocation getBackgroundImage() 
	{
		return new ResourceLocation(Reference.MOD_ID + ":textures/gui/container/creative_inventory/tab_maintab.png");
	}

	@SideOnly(Side.CLIENT)
	@Override
	public ItemStack getTabIconItem() 
	{
		return new ItemStack(ItemInit.MORINTROLL_APPLE);
	}
}
