package me.ICoding.fanstaia.enchants;

import me.ICoding.fanstaia.init.EnchantmentInit;
import me.ICoding.fanstaia.util.Reference;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;

public class EnchantmentTest extends Enchantment 
{
    public EnchantmentTest()
    {
        super(Rarity.VERY_RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND});
        this.setName("test");
        this.setRegistryName(new ResourceLocation(Reference.MOD_ID + ":test"));

        EnchantmentInit.ENCHANTMENTS.add(this);
    }

    @Override
    public int getMinEnchantability(int enchantmentLevel)
    {
        return 20 * enchantmentLevel;
    }

    @Override
    public int getMaxEnchantability(int enchantmentLevel)
    {
        return this.getMinEnchantability(enchantmentLevel) * 10;
    }

    @Override
    public int getMaxLevel()
    {
        return 5;
    }

    @Override
    protected boolean canApplyTogether(Enchantment ench)
    {
        return super.canApplyTogether(ench) && true; 
    }
}
