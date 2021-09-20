package me.ICoding.fanstaia.objects.items;

import me.ICoding.fanstaia.Main;
import me.ICoding.fanstaia.init.ItemInit;
import me.ICoding.fanstaia.util.handlers.ConfigHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemGuideBook extends Item
{
	public ItemGuideBook(String name) 
	{
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.MAIN_TAB);
		
		ItemInit.ITEMS.add(this);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) 
	{
		playerIn.openGui(Main.instance, ConfigHandler.GUI_GUIDE_BOOK, worldIn, handIn == EnumHand.MAIN_HAND ? 1 : 0, 0, 0);
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
}
