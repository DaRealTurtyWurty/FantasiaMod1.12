package me.ICoding.fanstaia.util.handlers;

import me.ICoding.fanstaia.init.BlockInit;
import me.ICoding.fanstaia.objects.blocks.cherry.chest.ContainerCherryChest;
import me.ICoding.fanstaia.objects.blocks.cherry.chest.GuiCherryChest;
import me.ICoding.fanstaia.objects.blocks.cherry.chest.TileEntityCherryChest;
import me.ICoding.fanstaia.objects.blocks.vlum.chest.ContainerVlumChest;
import me.ICoding.fanstaia.objects.blocks.vlum.chest.GuiVlumChest;
import me.ICoding.fanstaia.objects.blocks.vlum.chest.TileEntityVlumChest;
import me.ICoding.fanstaia.objects.blocks.vuzine.chest.ContainerVuzineChest;
import me.ICoding.fanstaia.objects.blocks.vuzine.chest.GuiVuzineChest;
import me.ICoding.fanstaia.objects.blocks.vuzine.chest.TileEntityVuzineChest;
import me.ICoding.fanstaia.objects.items.GuiFantasiaGuideBook;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GuiHandler implements IGuiHandler
{
	@Override
	@SideOnly(Side.CLIENT)
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		if(world.getBlockState(new BlockPos(x,y,z)).getBlock() == BlockInit.CHERRY_CHEST)
		{
			return new GuiCherryChest(player.inventory, (TileEntityCherryChest)world.getTileEntity(new BlockPos(x, y, z)));
		}
		if(world.getBlockState(new BlockPos(x,y,z)).getBlock() == BlockInit.VLUM_CHEST)
		{
			return new GuiVlumChest(player.inventory, (TileEntityVlumChest)world.getTileEntity(new BlockPos(x, y, z)));
		}
		if(world.getBlockState(new BlockPos(x,y,z)).getBlock() == BlockInit.VUZINE_CHEST)
		{
			return new GuiVuzineChest(player.inventory, (TileEntityVuzineChest)world.getTileEntity(new BlockPos(x, y, z)));
		}
		if(ID == ConfigHandler.GUI_GUIDE_BOOK)
		{
			EnumHand hand = x == 1 ? EnumHand.MAIN_HAND : EnumHand.OFF_HAND;
			return new GuiFantasiaGuideBook(player.getHeldItem(hand));
		}
		return null;
	}
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		if(world.getBlockState(new BlockPos(x,y,z)).getBlock() == BlockInit.CHERRY_CHEST)
		{
			return new ContainerCherryChest(player.inventory, (TileEntityCherryChest)world.getTileEntity(new BlockPos(x, y, z)), player);
		}
		if(world.getBlockState(new BlockPos(x,y,z)).getBlock() == BlockInit.VLUM_CHEST)
		{
			return new ContainerVlumChest(player.inventory, (TileEntityVlumChest)world.getTileEntity(new BlockPos(x, y, z)), player);
		}
		if(world.getBlockState(new BlockPos(x,y,z)).getBlock() == BlockInit.VUZINE_CHEST)
		{
			return new ContainerVuzineChest(player.inventory, (TileEntityVuzineChest)world.getTileEntity(new BlockPos(x, y, z)), player);
		}
		return null;
	}
}
