package me.ICoding.fanstaia.init;

import me.ICoding.fanstaia.util.handlers.ConfigHandler;
import me.ICoding.fanstaia.world.gen.dimension.WorldProviderFantasia;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;

public class DimensionInit 
{
	public static final DimensionType FANTASIA = DimensionType.register("Fantasia", "_fantasia", ConfigHandler.FANTASIA_DIM, WorldProviderFantasia.class, false);

	public static void registerDimensions()
	{
		DimensionManager.registerDimension(ConfigHandler.FANTASIA_DIM, FANTASIA);
	}
}
