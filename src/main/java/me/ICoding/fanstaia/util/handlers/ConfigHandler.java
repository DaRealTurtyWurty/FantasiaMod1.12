package me.ICoding.fanstaia.util.handlers;

import java.io.File;

import me.ICoding.fanstaia.Main;
import me.ICoding.fanstaia.util.Reference;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ConfigHandler 
{
	public static Configuration config;
	
	public static int CHERRY_BOAT = 457;
	public static int VLUM_BOAT = 489;
	public static int VUZINE_BOAT = 490;
	public static int FANTASIA_DIM = 5;
	public static int GUI_GUIDE_BOOK = 378;
	
	public static void init(File file)
	{
		config = new Configuration(file);
		
		String category;
		
		category = "Entity IDs";
		config.addCustomCategoryComment(category, "Set IDs for each entity.");
		CHERRY_BOAT = config.getInt("Cherry Boat ID", category, 457, 120, 500, "ID for the Cherry Boat Entity");
		VLUM_BOAT = config.getInt("Vlum Boat ID", category, 489, 120, 500, "ID for the Vlum Boat Entity");
		VLUM_BOAT = config.getInt("Vuzine Boat ID", category, 490, 120, 500, "ID for the Vuzine Boat Entity");
		
		category = "Dimension IDs";
		FANTASIA_DIM = config.getInt("Fantasia Dimension ID", category, 5, 2, 500, "ID for the Fantasia Dimension");
		
		category = "GUI IDs";
		GUI_GUIDE_BOOK = config.getInt("Fantasia Guide Book ID", category, 378, 1, 500, "ID for the Fantasia Mod Guide Book");
		
		config.save();
	}
	
	public static void registerConfig(FMLPreInitializationEvent event)
	{
		Main.config = new File(event.getModConfigurationDirectory() + "/" + Reference.MOD_ID);
		Main.config.mkdirs();
		init(new File(Main.config.getPath(), Reference.MOD_ID + ".cfg"));
	}
}
