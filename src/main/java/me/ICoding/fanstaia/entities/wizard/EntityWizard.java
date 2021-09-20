package me.ICoding.fanstaia.entities.wizard;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;

public class EntityWizard extends EntityAgeable //implements INpc, IMerchant
{
	@Nullable
	private EntityPlayer questingPlayer;
	
	private static final EntityWizard.IQuestList[][][][] WIZARD_QUESTS = new EntityWizard.IQuestList[][][][] {};
	
	public EntityWizard(World worldIn) 
	{
		super(worldIn);
	}

	/*@Override
	public void setCustomer(EntityPlayer player) 
	{
		this.questingPlayer = player;
	}

	@Override
	public EntityPlayer getCustomer() 
	{
		return this.questingPlayer;
	}

	@Override
	public MerchantRecipeList getRecipes(EntityPlayer player) 
	{
		return WIZARD_QUESTS;
	}

	@Override
	public void setRecipes(MerchantRecipeList recipeList) 
	{
		
	}

	@Override
	public void useRecipe(MerchantRecipe recipe) 
	{
		
	}

	@Override
	public void verifySellingItem(ItemStack stack) 
	{
		
	}

	@Override
	public World getWorld() 
	{
		return null;
	}

	@Override
	public BlockPos getPos()
	{
		return null;
	}*/

	@Override
	public EntityAgeable createChild(EntityAgeable ageable) 
	{
		return null;
	}
	
	public interface IQuestList
    {
        void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random);
    }
}
