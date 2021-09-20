package me.ICoding.fanstaia.init;

import java.util.ArrayList;
import java.util.List;

import me.ICoding.fanstaia.objects.ItemBase;
import me.ICoding.fanstaia.objects.items.ItemCherryBoat;
import me.ICoding.fanstaia.objects.items.ItemMagicalBean;
import me.ICoding.fanstaia.objects.items.ItemMorintrollApple;
import me.ICoding.fanstaia.objects.items.ItemVlumBoat;
import me.ICoding.fanstaia.objects.items.ItemVuzineBoat;
import net.minecraft.item.Item;

public class ItemInit 
{
	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	public static final Item CHERRY_STICK = new ItemBase("cherry_stick");
	public static final Item CHERRY_BOAT = new ItemCherryBoat("cherry_boat");
	
	public static final Item VLUM_BOAT = new ItemVlumBoat("vlum_boat");
	public static final Item VLUM_STICK = new ItemBase("vlum_stick");
	
	public static final Item VUZINE_BOAT = new ItemVuzineBoat("vuzine_boat");
	public static final Item VUZINE_STICK = new ItemBase("vuzine_stick");
	
	public static final Item MORINTROLL_APPLE = new ItemMorintrollApple("morintroll_apple", 4, 2.4f, false);
	//public static final Item FANTASIA_GUIDE_BOOK = new ItemGuideBook("fantasia_guide");
	public static final Item MAGICAL_BEAN = new ItemMagicalBean("magical_bean");
}
