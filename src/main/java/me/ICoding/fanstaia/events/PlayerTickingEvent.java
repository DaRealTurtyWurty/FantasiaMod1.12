package me.ICoding.fanstaia.events;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.ListenerList;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber
public class PlayerTickingEvent 
{
	@SuppressWarnings("unused")
	@SubscribeEvent
	public static void playerTickingEvent(PlayerTickEvent event)
	{
		EntityPlayer player = event.player;
		World world = player.world;
		TickEvent.Type type = event.type;
		TickEvent.Phase phase = event.phase;
		Side side = event.side;
		boolean canceled = event.isCanceled();
		ListenerList listenList = event.getListenerList();
		Event.Result result = event.getResult();
		int hashCode = event.hashCode();
		boolean hasResult = event.hasResult();
		boolean cancelled = event.isCanceled();
		BlockPos pos = new BlockPos(player.posX, player.posY, player.posZ);
		InventoryPlayer playerInv = player.inventory;
		Container playerCont = player.inventoryContainer;
		List<Slot> invSlots = playerCont.inventorySlots;
		NonNullList<ItemStack> armorSlots = playerInv.armorInventory;
		PlayerCapabilities playerCaps = player.capabilities;
		
		
	}
}
