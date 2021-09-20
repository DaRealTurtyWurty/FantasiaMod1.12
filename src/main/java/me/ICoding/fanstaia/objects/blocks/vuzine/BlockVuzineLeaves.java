package me.ICoding.fanstaia.objects.blocks.vuzine;

import java.util.List;
import java.util.Random;

import me.ICoding.fanstaia.Main;
import me.ICoding.fanstaia.init.BlockInit;
import me.ICoding.fanstaia.init.ItemInit;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockVuzineLeaves extends Block implements IShearable {
	int[] surroundings;

	public BlockVuzineLeaves(String name) {
		super(Material.LEAVES);
		setTickRandomly(true);
		setHardness(0.2F);
		setLightOpacity(1);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.MAIN_TAB);
		setSoundType(SoundType.PLANT);

		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}

	@SuppressWarnings("deprecation")
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		if (worldIn.isRainingAt(pos.up()) && !worldIn.getBlockState(pos.down()).isTopSolid() && rand.nextInt(15) == 1) {
			double d0 = (double) ((float) pos.getX() + rand.nextFloat());
			double d1 = (double) pos.getY() - 0.05D;
			double d2 = (double) ((float) pos.getZ() + rand.nextFloat());
			worldIn.spawnParticle(EnumParticleTypes.DRIP_WATER, d0, d1, d2, 0.0D, 0.0D, 0.0D);
		}
	}

	public int quantityDropped(Random random) {
		return random.nextInt(20) == 0 ? 1 : 0;
	}

	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(Blocks.SAPLING);
	}

	public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {
		super.dropBlockAsItemWithChance(worldIn, pos, state, chance, fortune);
	}

	protected void dropApple(World worldIn, BlockPos pos, IBlockState state, int chance) {
	}

	protected int getSaplingDropChance(IBlockState state) {
		return 20;
	}

	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT_MIPPED;
	}

	public boolean causesSuffocation(IBlockState state) {
		return false;
	}

	@Override
	public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos) {
		return true;
	}

	@Override
	public boolean isLeaves(IBlockState state, IBlockAccess world, BlockPos pos) {
		return true;
	}

	@Override
	public void getDrops(net.minecraft.util.NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos,
			IBlockState state, int fortune) {
		Random rand = world instanceof World ? ((World) world).rand : new Random();
		int chance = this.getSaplingDropChance(state);

		if (fortune > 0) {
			chance -= 2 << fortune;
			if (chance < 10)
				chance = 10;
		}

		if (rand.nextInt(chance) == 0) {
			ItemStack drop = new ItemStack(getItemDropped(state, rand, fortune), 1, damageDropped(state));
			if (!drop.isEmpty())
				drops.add(drop);
		}

		chance = 200;
		if (fortune > 0) {
			chance -= 10 << fortune;
			if (chance < 40)
				chance = 40;
		}

		this.captureDrops(true);
		if (world instanceof World)
			this.dropApple((World) world, pos, state, chance); // Dammet mojang
		drops.addAll(this.captureDrops(false));
	}

	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
		return NonNullList.withSize(1, new ItemStack(this));
	}
}