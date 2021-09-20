package me.ICoding.fanstaia.util.handlers;

import me.ICoding.fanstaia.Main;
import me.ICoding.fanstaia.init.BiomeInit;
import me.ICoding.fanstaia.init.BlockInit;
import me.ICoding.fanstaia.init.DimensionInit;
import me.ICoding.fanstaia.init.EntityInit;
import me.ICoding.fanstaia.init.ItemInit;
import me.ICoding.fanstaia.objects.blocks.cherry.chest.TileEntityCherryChest;
import me.ICoding.fanstaia.objects.blocks.cherry.chest.TileEntityCherryChestRenderer;
import me.ICoding.fanstaia.objects.blocks.tree_structureblock.TileEntityTreeStrucSpecialRenderer;
import me.ICoding.fanstaia.objects.blocks.tree_structureblock.TileEntityTreeStructureBlock;
import me.ICoding.fanstaia.objects.blocks.vlum.chest.TileEntityVlumChest;
import me.ICoding.fanstaia.objects.blocks.vlum.chest.TileEntityVlumChestRenderer;
import me.ICoding.fanstaia.objects.blocks.vuzine.chest.TileEntityVuzineChest;
import me.ICoding.fanstaia.objects.blocks.vuzine.chest.TileEntityVuzineChestRenderer;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@EventBusSubscriber
public class RegistryHandler 
{
	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event)
	{
		event.getRegistry().registerAll(ItemInit.ITEMS.toArray(new Item[0]));
	}

	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().registerAll(BlockInit.BLOCKS.toArray(new Block[0]));
		TileEntityHandler.registerTileEntities();
	}
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event)
	{
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCherryChest.class, new TileEntityCherryChestRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityVlumChest.class, new TileEntityVlumChestRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityVuzineChest.class, new TileEntityVuzineChestRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTreeStructureBlock.class, new TileEntityTreeStrucSpecialRenderer());
		for(Item item : ItemInit.ITEMS)
		{
			Main.proxy.registerItemRenderer(item, 0, "inventory");
		}
		
		for(Block block : BlockInit.BLOCKS)
		{
			Main.proxy.registerItemRenderer(Item.getItemFromBlock(block), 0, "inventory");
		}
		RenderHandler.registerEntityRenderers();
	}
	
	public static void preInitRegistries(FMLPreInitializationEvent event)
	{
		BiomeInit.registerBiomes();
		DimensionInit.registerDimensions();
		EntityInit.registerEntities();
		ConfigHandler.registerConfig(event);
	}
	
	public static void initRegistries()
	{
		NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new GuiHandler());
		EnumHelper.addArt("Test", "Test", 64, 32, 96, 64);
	}
	
	public static void postInitRegistries()
	{
		
	}
	
	public static void serverInitRegistries()
	{
		
	}
}
