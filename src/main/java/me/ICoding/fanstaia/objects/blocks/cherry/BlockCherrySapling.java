package me.ICoding.fanstaia.objects.blocks.cherry;

import java.util.Random;

import me.ICoding.fanstaia.Main;
import me.ICoding.fanstaia.init.BlockInit;
import me.ICoding.fanstaia.init.ItemInit;
import me.ICoding.fanstaia.world.gen.features.trees.WorldGenCherryTree;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BlockCherrySapling extends BlockBush implements IGrowable {
	public static final PropertyEnum<BlockCherrySapling.EnumType> TYPE = PropertyEnum.<BlockCherrySapling.EnumType>create(
			"type", BlockCherrySapling.EnumType.class);
	public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 1);
	protected static final AxisAlignedBB SAPLING_AABB = new AxisAlignedBB(0.09999999403953552D, 0.0D,
			0.09999999403953552D, 0.8999999761581421D, 0.800000011920929D, 0.8999999761581421D);

	public BlockCherrySapling(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.MAIN_TAB);
		setSoundType(SoundType.PLANT);
		this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, BlockCherrySapling.EnumType.CHERRY)
				.withProperty(STAGE, Integer.valueOf(0)));

		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}

	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return SAPLING_AABB;
	}

	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (!worldIn.isRemote) {
			super.updateTick(worldIn, pos, state, rand);

			if (!worldIn.isAreaLoaded(pos, 1))
				return; // Forge: prevent loading unloaded chunks when checking neighbor's light
			if (worldIn.getLightFromNeighbors(pos.up()) >= 9 && rand.nextInt(7) == 0) {
				this.grow(worldIn, pos, state, rand);
			}
		}
	}

	public void grow(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (((Integer) state.getValue(STAGE)).intValue() == 0) {
			worldIn.setBlockState(pos, state.cycleProperty(STAGE), 4);
		} else {
			this.generateTree(worldIn, pos, state, rand);
		}
	}

	public void generateTree(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (!net.minecraftforge.event.terraingen.TerrainGen.saplingGrowTree(worldIn, rand, pos))
			return;
		WorldGenerator worldgenerator = (WorldGenerator) (rand.nextInt(10) == 0 ? new WorldGenCherryTree(rand.nextInt(1) == 0 ? BlockInit.PINK_CHERRY_LEAVES.getDefaultState() : BlockInit.WHITE_CHERRY_LEAVES.getDefaultState())
				: new WorldGenCherryTree(rand.nextInt(1) == 0 ? BlockInit.PINK_CHERRY_LEAVES.getDefaultState() : BlockInit.WHITE_CHERRY_LEAVES.getDefaultState()));
		int i = 0;
		int j = 0;
		boolean flag = false;

		IBlockState iblockstate2 = Blocks.AIR.getDefaultState();

		if (flag) {
			worldIn.setBlockState(pos.add(i, 0, j), iblockstate2, 4);
			worldIn.setBlockState(pos.add(i + 1, 0, j), iblockstate2, 4);
			worldIn.setBlockState(pos.add(i, 0, j + 1), iblockstate2, 4);
			worldIn.setBlockState(pos.add(i + 1, 0, j + 1), iblockstate2, 4);
		} else {
			worldIn.setBlockState(pos, iblockstate2, 4);
		}

		if (!worldgenerator.generate(worldIn, rand, pos.add(i, 0, j))) {
			if (flag) {
				worldIn.setBlockState(pos.add(i, 0, j), state, 4);
				worldIn.setBlockState(pos.add(i + 1, 0, j), state, 4);
				worldIn.setBlockState(pos.add(i, 0, j + 1), state, 4);
				worldIn.setBlockState(pos.add(i + 1, 0, j + 1), state, 4);
			} else {
				worldIn.setBlockState(pos, state, 4);
			}
		}
	}

	/**
	 * Check whether the given BlockPos has a Sapling of the given type
	 */
	public boolean isTypeAt(World worldIn, BlockPos pos, BlockCherrySapling.EnumType type) {
		IBlockState iblockstate = worldIn.getBlockState(pos);
		return iblockstate.getBlock() == this && iblockstate.getValue(TYPE) == type;
	}

	/**
	 * Gets the metadata of the item this Block can drop. This method is called when
	 * the block gets destroyed. It returns the metadata of the dropped item based
	 * on the old metadata of the block.
	 */
	public int damageDropped(IBlockState state) {
		return ((BlockCherrySapling.EnumType) state.getValue(TYPE)).getMetadata();
	}

	/**
	 * returns a list of blocks with the same ID, but different meta (eg: wood
	 * returns 4 blocks)
	 */
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
		for (BlockCherrySapling.EnumType blockplanks$enumtype : BlockCherrySapling.EnumType.values()) {
			items.add(new ItemStack(this, 1, blockplanks$enumtype.getMetadata()));
		}
	}

	/**
	 * Whether this IGrowable can grow
	 */
	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
		return true;
	}

	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		return (double) worldIn.rand.nextFloat() < 0.45D;
	}

	public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		this.grow(worldIn, pos, state, rand);
	}

	/**
	 * Convert the given metadata into a BlockState for this Block
	 */
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(TYPE, BlockCherrySapling.EnumType.byMetadata(meta & 7))
				.withProperty(STAGE, Integer.valueOf((meta & 8) >> 3));
	}

	/**
	 * Convert the BlockState into the correct metadata value
	 */
	public int getMetaFromState(IBlockState state) {
		int i = 0;
		i = i | ((BlockCherrySapling.EnumType) state.getValue(TYPE)).getMetadata();
		i = i | ((Integer) state.getValue(STAGE)).intValue() << 3;
		return i;
	}

	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { TYPE, STAGE });
	}

	public static enum EnumType implements IStringSerializable {
		CHERRY(0, "cherry", MapColor.RED_STAINED_HARDENED_CLAY);

		private static final BlockCherrySapling.EnumType[] META_LOOKUP = new BlockCherrySapling.EnumType[values().length];
		private final int meta;
		private final String name;
		private final String unlocalizedName;
		/** The color that represents this entry on a map. */
		private final MapColor mapColor;

		private EnumType(int metaIn, String nameIn, MapColor mapColorIn) {
			this(metaIn, nameIn, nameIn, mapColorIn);
		}

		private EnumType(int metaIn, String nameIn, String unlocalizedNameIn, MapColor mapColorIn) {
			this.meta = metaIn;
			this.name = nameIn;
			this.unlocalizedName = unlocalizedNameIn;
			this.mapColor = mapColorIn;
		}

		public int getMetadata() {
			return this.meta;
		}

		/**
		 * The color which represents this entry on a map.
		 */
		public MapColor getMapColor() {
			return this.mapColor;
		}

		public String toString() {
			return this.name;
		}

		public static BlockCherrySapling.EnumType byMetadata(int meta) {
			if (meta < 0 || meta >= META_LOOKUP.length) {
				meta = 0;
			}

			return META_LOOKUP[meta];
		}

		public String getName() {
			return this.name;
		}

		public String getUnlocalizedName() {
			return this.unlocalizedName;
		}

		static {
			for (BlockCherrySapling.EnumType blockplanks$enumtype : values()) {
				META_LOOKUP[blockplanks$enumtype.getMetadata()] = blockplanks$enumtype;
			}
		}
	}
}