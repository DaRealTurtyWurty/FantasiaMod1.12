package me.ICoding.fanstaia.init;

import java.util.ArrayList;
import java.util.List;

import me.ICoding.fanstaia.enchants.EnchantmentTest;
import me.ICoding.fanstaia.util.Reference;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class EnchantmentInit
{
    public static final List<Enchantment> ENCHANTMENTS = new ArrayList<Enchantment>();

    public static final Enchantment LIFE_STEAL = new EnchantmentTest();

    @SubscribeEvent
	public static void lifeSteal(LivingAttackEvent event)
    {
		Object attacker = event.getSource().getTrueSource();
		if (attacker instanceof EntityLivingBase) 
		{
			EntityLivingBase entityAttacker = (EntityLivingBase)attacker;
			if(!entityAttacker.getEntityWorld().isRemote) 
			{
				int level = EnchantmentHelper.getEnchantmentLevel(LIFE_STEAL, entityAttacker.getHeldItemMainhand());
				entityAttacker.heal(event.getAmount() * (level / 5));
			}
		}	
	}	
}