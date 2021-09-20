package me.ICoding.fanstaia.init;

import me.ICoding.fanstaia.Main;
import me.ICoding.fanstaia.entities.EntityCherryBoat;
import me.ICoding.fanstaia.entities.EntityVlumBoat;
import me.ICoding.fanstaia.entities.EntityVuzineBoat;
import me.ICoding.fanstaia.util.Reference;
import me.ICoding.fanstaia.util.handlers.ConfigHandler;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityInit 
{
	public static void registerEntities()
	{
		registerNonMobEntity(new ResourceLocation(Reference.MOD_ID + ":cherry_boat"), EntityCherryBoat.class, "cherry_boat", ConfigHandler.CHERRY_BOAT, Main.instance, 64, 32, true);
		registerNonMobEntity(new ResourceLocation(Reference.MOD_ID + ":vlum_boat"), EntityVlumBoat.class, "vlum_boat", ConfigHandler.VLUM_BOAT, Main.instance, 64, 32, true);
		registerNonMobEntity(new ResourceLocation(Reference.MOD_ID + ":vuzine_boat"), EntityVuzineBoat.class, "vuzine_boat", ConfigHandler.VUZINE_BOAT, Main.instance, 64, 32, true);
	}
	
	private static void registerEntity(ResourceLocation registryName, Class<? extends Entity> entityClass, String entityName, int id, Object mod, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates, int eggPrimary, int eggSecondary)
	{
		EntityRegistry.registerModEntity(registryName, entityClass, entityName, id, mod, trackingRange, updateFrequency, sendsVelocityUpdates, eggPrimary, eggSecondary);
	}
	
	private static void registerNonMobEntity(ResourceLocation registryName, Class<? extends Entity> entityClass, String entityName, int id, Object mod, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates) 
	{
		EntityRegistry.registerModEntity(registryName, entityClass, entityName, id, mod, trackingRange, updateFrequency, sendsVelocityUpdates);
	}
}
