package me.ICoding.fanstaia;

import java.io.File;

import me.ICoding.fanstaia.proxy.CommonProxy;
import me.ICoding.fanstaia.tabs.MainTab;
import me.ICoding.fanstaia.util.Reference;
import me.ICoding.fanstaia.util.handlers.RegistryHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION)
public class Main 
{
	public static File config;
	
	public static final CreativeTabs MAIN_TAB = new MainTab("maintab");

	@Instance
	public static Main instance;

	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;

	@EventHandler
	public static void preInit(FMLPreInitializationEvent event) 
	{
		RegistryHandler.preInitRegistries(event);
	}

	@EventHandler
	public static void init(FMLInitializationEvent event) 
	{
		RegistryHandler.initRegistries();
	}

	@EventHandler
	public static void postInit(FMLPostInitializationEvent event) 
	{
		RegistryHandler.postInitRegistries();
	}

	@EventHandler
	public static void serverInit(FMLServerStartingEvent event) 
	{

	}
}
