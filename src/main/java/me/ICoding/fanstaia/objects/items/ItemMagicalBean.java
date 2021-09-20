package me.ICoding.fanstaia.objects.items;

import me.ICoding.fanstaia.Main;
import me.ICoding.fanstaia.init.BlockInit;
import me.ICoding.fanstaia.init.ItemInit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemMagicalBean extends Item
{
	public ItemMagicalBean(String name) 
	{
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.MAIN_TAB);
		
		ItemInit.ITEMS.add(this);
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) 
	{
		if(worldIn.getBlockState(pos).getBlock() == Blocks.GRASS)
		{
			worldIn.setBlockState(pos.up(), BlockInit.MAGICAL_BEAN_PLANT.getDefaultState());
			if(!player.capabilities.isCreativeMode)
			{
				player.getHeldItem(hand).shrink(1);
				return EnumActionResult.SUCCESS;
			}
			return EnumActionResult.SUCCESS;
		}
		return EnumActionResult.FAIL;
	}
}
