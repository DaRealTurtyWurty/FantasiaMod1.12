package me.ICoding.fanstaia.objects.blocks.vlum;

import me.ICoding.fanstaia.Main;
import me.ICoding.fanstaia.init.BlockInit;
import me.ICoding.fanstaia.init.ItemInit;
import net.minecraft.block.BlockLog;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class BlockVlumLog extends BlockLog {
	public BlockVlumLog(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.MAIN_TAB);
		setHardness(2.0F);
		setSoundType(SoundType.WOOD);
		setDefaultState(blockState.getBaseState().withProperty(LOG_AXIS, BlockLog.EnumAxis.Y));

		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}

	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
		items.add(new ItemStack(this));
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		IBlockState state = this.getDefaultState();

		switch (meta & 12) {
		case 0:
			state = state.withProperty(LOG_AXIS, BlockLog.EnumAxis.Y);
			break;
		case 4:
			state = state.withProperty(LOG_AXIS, BlockLog.EnumAxis.X);
			break;
		case 8:
			state = state.withProperty(LOG_AXIS, BlockLog.EnumAxis.Z);
			break;
		default:
			state = state.withProperty(LOG_AXIS, BlockLog.EnumAxis.NONE);
		}

		return state;
	}

	@Override
	@SuppressWarnings("incomplete-switch")
	public int getMetaFromState(IBlockState state) {
		int meta = 0;

		switch (state.getValue(LOG_AXIS)) {
		case X:
			meta |= 4;
			break;
		case Z:
			meta |= 8;
			break;
		case NONE:
			meta |= 12;
		}

		return meta;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { LOG_AXIS });
	}

	@Override
	protected ItemStack getSilkTouchDrop(IBlockState state) {
		return new ItemStack(Item.getItemFromBlock(this), 1);
	}

	@Override
	public int damageDropped(IBlockState state) {
		return 0;
	}
}
