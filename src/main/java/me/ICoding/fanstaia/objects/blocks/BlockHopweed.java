package me.ICoding.fanstaia.objects.blocks;

import me.ICoding.fanstaia.Main;
import me.ICoding.fanstaia.init.BlockInit;
import me.ICoding.fanstaia.init.ItemInit;
import net.minecraft.block.BlockBush;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemBlock;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockHopweed extends BlockBush
{
	private static final AxisAlignedBB HOPWEED_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5625D, 1.0D);
	
	public BlockHopweed(String name) 
	{
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.MAIN_TAB);
		
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) 
	{
		return HOPWEED_AABB;
	}
	
	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) 
	{
		if(entityIn instanceof EntityLiving)
		{
			EntityLiving entity = (EntityLiving)entityIn;
			entity.addPotionEffect(new PotionEffect(MobEffects.LEVITATION, 10, 5));
		}
		
		else if(entityIn instanceof EntityPlayer) 
		{
			EntityPlayer player = (EntityPlayer)entityIn;
			player.addPotionEffect(new PotionEffect(MobEffects.LEVITATION, 10, 5));
		}
		
		else if(entityIn instanceof EntityLivingBase)
		{
			EntityLivingBase entityBase = (EntityLivingBase)entityIn;
			entityBase.addPotionEffect(new PotionEffect(MobEffects.LEVITATION, 10, 5));
		} 
		
		else if(entityIn instanceof EntityArmorStand || entityIn instanceof EntityFallingBlock || entityIn instanceof EntityTNTPrimed) 
		{
			if(!(entityIn instanceof EntityTNTPrimed))
			{
				entityIn.setPositionAndUpdate(entityIn.getPosition().getX(), entityIn.getPosition().getY() + 1, entityIn.getPosition().getZ());
			}
			else
			{
				entityIn.setPositionAndUpdate(entityIn.getPosition().getX(), entityIn.getPosition().getY() + 5, entityIn.getPosition().getZ());
			}
		}
	}
}
