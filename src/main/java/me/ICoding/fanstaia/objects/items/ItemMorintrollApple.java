package me.ICoding.fanstaia.objects.items;

import me.ICoding.fanstaia.Main;
import me.ICoding.fanstaia.init.ItemInit;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemMorintrollApple extends ItemFood
{
	public ItemMorintrollApple(String name, int amount, float saturation, boolean isWolfFood) 
	{
		super(amount, saturation, isWolfFood);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.MAIN_TAB);
		
		ItemInit.ITEMS.add(this);
	}
	
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) 
	{
		if(entityLiving instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)entityLiving;
			player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D);
			player.setHealth(player.getMaxHealth());
			player.capabilities.allowFlying = true;
		}
		return super.onItemUseFinish(stack, worldIn, entityLiving);
	}
}
