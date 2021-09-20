package me.ICoding.fanstaia.util.handlers;

import me.ICoding.fanstaia.objects.blocks.cherry.chest.TileEntityCherryChest;
import me.ICoding.fanstaia.objects.blocks.tree_structureblock.TileEntityTreeStructureBlock;
import me.ICoding.fanstaia.objects.blocks.vlum.chest.TileEntityVlumChest;
import me.ICoding.fanstaia.objects.blocks.vuzine.chest.TileEntityVuzineChest;
import me.ICoding.fanstaia.util.Reference;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler 
{
	@SuppressWarnings("deprecation")
	public static void registerTileEntities()
	{
		GameRegistry.registerTileEntity(TileEntityCherryChest.class, Reference.MOD_ID + ":cherry_chest");
		GameRegistry.registerTileEntity(TileEntityVlumChest.class, Reference.MOD_ID + ":vlum_chest");
		GameRegistry.registerTileEntity(TileEntityVuzineChest.class, Reference.MOD_ID + ":vuzine_chest");
		GameRegistry.registerTileEntity(TileEntityTreeStructureBlock.class, Reference.MOD_ID + ":tree_structure");
	}
}
