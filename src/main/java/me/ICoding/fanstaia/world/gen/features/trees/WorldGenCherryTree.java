package me.ICoding.fanstaia.world.gen.features.trees;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import me.ICoding.fanstaia.init.BlockInit;
import me.ICoding.fanstaia.objects.blocks.cherry.BlockCherryLog;
import me.ICoding.fanstaia.objects.blocks.cherry.BlockCherrySapling;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class WorldGenCherryTree extends WorldGenAbstractTree 
{
	private Random rand = new Random();
	
	private IBlockState log = BlockInit.CHERRY_LOG.getDefaultState();
	private IBlockState leaf;
	
	private World world;
	private BlockPos basePos = BlockPos.ORIGIN;
	int heightLimit;
	int height;
	double heightAttenuation = 0.847D;
	double branchSlope = 0.915D;
	double scaleWidth = 0.8D;
	double leafDensity = 1.7D;
	int trunkSize = 1;
	int heightLimitLimit = 14;
	/**
	 * Sets the distance limit for how far away the generator will populate leaves
	 * from the base leaf node.
	 */
	int leafDistanceLimit = 4;
	List<WorldGenCherryTree.FoliageCoordinates> foliageCoords;

	public WorldGenCherryTree(IBlockState state) {
		super(false);
		this.leaf = state;
	}

	/**
	 * Generates a list of leaf nodes for the tree, to be populated by
	 * generateLeaves.
	 */
	void generateLeafNodeList() {
		this.height = (int) ((double) this.heightLimit * this.heightAttenuation);

		if (this.height >= this.heightLimit) {
			this.height = this.heightLimit - 1;
		}

		int i = (int) (1.382D + Math.pow(this.leafDensity * (double) this.heightLimit / 13.0D, 2.0D));

		if (i < 1) {
			i = 1;
		}

		int j = this.basePos.getY() + this.height;
		int k = this.heightLimit - this.leafDistanceLimit;
		this.foliageCoords = Lists.<WorldGenCherryTree.FoliageCoordinates>newArrayList();
		this.foliageCoords.add(new WorldGenCherryTree.FoliageCoordinates(this.basePos.up(k), j));

		for (; k >= 0; --k) {
			float f = this.layerSize(k);

			if (f >= 0.0F) {
				for (int l = 0; l < i; ++l) {
					double d0 = this.scaleWidth * (double) f * ((double) rand.nextFloat() + 0.328D);
					double d1 = (double) (rand.nextFloat() * 2.0F) * Math.PI;
					double d2 = d0 * Math.sin(d1) + 0.5D;
					double d3 = d0 * Math.cos(d1) + 0.5D;
					BlockPos blockpos = this.basePos.add(d2, (double) (k - 1), d3);
					BlockPos blockpos1 = blockpos.up(this.leafDistanceLimit);

					if (this.checkBlockLine(blockpos, blockpos1) == -1) {
						int i1 = this.basePos.getX() - blockpos.getX();
						int j1 = this.basePos.getZ() - blockpos.getZ();
						double d4 = (double) blockpos.getY()
								- Math.sqrt((double) (i1 * i1 + j1 * j1)) * this.branchSlope;
						int k1 = d4 > (double) j ? j : (int) d4;
						BlockPos blockpos2 = new BlockPos(this.basePos.getX(), k1, this.basePos.getZ());

						if (this.checkBlockLine(blockpos2, blockpos) == -1) {
							this.foliageCoords
									.add(new WorldGenCherryTree.FoliageCoordinates(blockpos, blockpos2.getY()));
						}
					}
				}
			}
		}
	}

	void crosSection(BlockPos pos, float unknownF, IBlockState state1) {
		int i = (int) ((double) unknownF + 0.618D);

		for (int j = -i; j <= i; ++j) {
			for (int k = -i; k <= i; ++k) {
				if (Math.pow((double) Math.abs(j) + 0.5D, 2.0D)
						+ Math.pow((double) Math.abs(k) + 0.5D, 2.0D) <= (double) (unknownF * unknownF)) {
					BlockPos blockpos = pos.add(j, 0, k);
					IBlockState state = this.world.getBlockState(blockpos);

					if (state.getBlock().isAir(state, world, blockpos)
							|| state.getBlock().isLeaves(state, world, blockpos)) {
						this.setBlockAndNotifyAdequately(this.world, blockpos, state1);
					}
				}
			}
		}
	}

	/**
	 * Gets the rough size of a layer of the tree.
	 */
	float layerSize(int y) {
		if ((float) y < (float) this.heightLimit * 0.3F) {
			return -1.0F;
		} else {
			float f = (float) this.heightLimit / 2.0F;
			float f1 = f - (float) y;
			float f2 = MathHelper.sqrt(f * f - f1 * f1);

			if (f1 == 0.0F) {
				f2 = f;
			} else if (Math.abs(f1) >= f) {
				return 0.0F;
			}

			return f2 * 0.5F;
		}
	}

	float leafSize(int y) {
		if (y >= 0 && y < this.leafDistanceLimit) {
			return y != 0 && y != this.leafDistanceLimit - 1 ? 3.0F : 2.0F;
		} else {
			return -1.0F;
		}
	}

	/**
	 * Generates the leaves surrounding an individual entry in the leafNodes list.
	 */
	void generateLeafNode(BlockPos pos) {
		for (int i = 0; i < this.leafDistanceLimit; ++i) {
			this.crosSection(pos.up(i), this.leafSize(i), leaf);
		}
	}

	void limb(BlockPos pos1, BlockPos pos2, Block pos3) {
		BlockPos blockpos = pos2.add(-pos1.getX(), -pos1.getY(), -pos1.getZ());
		int i = this.getGreatestDistance(blockpos);
		float f = (float) blockpos.getX() / (float) i;
		float f1 = (float) blockpos.getY() / (float) i;
		float f2 = (float) blockpos.getZ() / (float) i;

		for (int j = 0; j <= i; ++j) {
			BlockPos blockpos1 = pos1.add((double) (0.5F + (float) j * f), (double) (0.5F + (float) j * f1),
					(double) (0.5F + (float) j * f2));
			BlockCherryLog.EnumAxis blocklog$enumaxis = this.getLogAxis(pos1, blockpos1);
			this.setBlockAndNotifyAdequately(this.world, blockpos1,
					pos3.getDefaultState().withProperty(BlockCherryLog.LOG_AXIS, blocklog$enumaxis));
		}
	}

	/**
	 * Returns the absolute greatest distance in the BlockPos object.
	 */
	private int getGreatestDistance(BlockPos posIn) {
		int i = MathHelper.abs(posIn.getX());
		int j = MathHelper.abs(posIn.getY());
		int k = MathHelper.abs(posIn.getZ());

		if (k > i && k > j) {
			return k;
		} else {
			return j > i ? j : i;
		}
	}

	private BlockLog.EnumAxis getLogAxis(BlockPos pos1, BlockPos pos2) {
		BlockCherryLog.EnumAxis blocklog$enumaxis = BlockCherryLog.EnumAxis.Y;
		int i = Math.abs(pos2.getX() - pos1.getX());
		int j = Math.abs(pos2.getZ() - pos1.getZ());
		int k = Math.max(i, j);

		if (k > 0) {
			if (i == k) {
				blocklog$enumaxis = BlockCherryLog.EnumAxis.X;
			} else if (j == k) {
				blocklog$enumaxis = BlockCherryLog.EnumAxis.Z;
			}
		}

		return blocklog$enumaxis;
	}

	/**
	 * Generates the leaf portion of the tree as specified by the leafNodes list.
	 */
	void generateLeaves() {
		for (WorldGenCherryTree.FoliageCoordinates WorldGenCherryTree$foliagecoordinates : this.foliageCoords) {
			this.generateLeafNode(WorldGenCherryTree$foliagecoordinates);
		}
	}

	/**
	 * Indicates whether or not a leaf node requires additional wood to be added to
	 * preserve integrity.
	 */
	boolean leafNodeNeedsBase(int amount) {
		return (double) amount >= (double) this.heightLimit * 0.2D;
	}

	/**
	 * Places the trunk for the big tree that is being generated. Able to generate
	 * double-sized trunks by changing a field that is always 1 to 2.
	 */
	void generateTrunk() {
		BlockPos blockpos = this.basePos;
		BlockPos blockpos1 = this.basePos.up(this.height);
		Block block = log.getBlock();
		this.limb(blockpos, blockpos1, block);

		if (this.trunkSize == 2) {
			this.limb(blockpos.east(), blockpos1.east(), block);
			this.limb(blockpos.east().south(), blockpos1.east().south(), block);
			this.limb(blockpos.south(), blockpos1.south(), block);
		}
	}

	/**
	 * Generates additional wood blocks to fill out the bases of different leaf
	 * nodes that would otherwise degrade.
	 */
	void generateLeafNodeBases() {
		for (WorldGenCherryTree.FoliageCoordinates WorldGenCherryTree$foliagecoordinates : this.foliageCoords) {
			int i = WorldGenCherryTree$foliagecoordinates.getBranchBase();
			BlockPos blockpos = new BlockPos(this.basePos.getX(), i, this.basePos.getZ());

			if (!blockpos.equals(WorldGenCherryTree$foliagecoordinates)
					&& this.leafNodeNeedsBase(i - this.basePos.getY())) {
				this.limb(blockpos, WorldGenCherryTree$foliagecoordinates, log.getBlock());
			}
		}
	}

	/**
	 * Checks a line of blocks in the world from the first coordinate to triplet to
	 * the second, returning the distance (in blocks) before a non-air, non-leaf
	 * block is encountered and/or the end is encountered.
	 */
	int checkBlockLine(BlockPos posOne, BlockPos posTwo) {
		BlockPos blockpos = posTwo.add(-posOne.getX(), -posOne.getY(), -posOne.getZ());
		int i = this.getGreatestDistance(blockpos);
		float f = (float) blockpos.getX() / (float) i;
		float f1 = (float) blockpos.getY() / (float) i;
		float f2 = (float) blockpos.getZ() / (float) i;

		if (i == 0) {
			return -1;
		} else {
			for (int j = 0; j <= i; ++j) {
				BlockPos blockpos1 = posOne.add((double) (0.5F + (float) j * f), (double) (0.5F + (float) j * f1),
						(double) (0.5F + (float) j * f2));

				if (!this.isReplaceable(world, blockpos1)) {
					return j;
				}
			}

			return -1;
		}
	}

	public void setDecorationDefaults() {
		this.leafDistanceLimit = 5;
	}

	public boolean generate(World worldIn, Random rand, BlockPos position) {
		this.world = worldIn;
		this.basePos = position;
		rand = new Random(rand.nextLong());

		if (this.heightLimit == 0) {
			this.heightLimit = 5 + rand.nextInt(this.heightLimitLimit);
		}

		if (!this.validTreeLocation()) {
			this.world = null; // Fix vanilla Mem leak, holds latest world
			return false;
		} else {
			this.generateLeafNodeList();
			this.generateLeaves();
			this.generateTrunk();
			this.generateLeafNodeBases();
			this.world = null; // Fix vanilla Mem leak, holds latest world
			return true;
		}
	}

	/**
	 * Returns a boolean indicating whether or not the current location for the
	 * tree, spanning basePos to to the height limit, is valid.
	 */
	private boolean validTreeLocation() {
		BlockPos down = this.basePos.down();
		net.minecraft.block.state.IBlockState state = this.world.getBlockState(down);
		boolean isSoil = state.getBlock().canSustainPlant(state, this.world, down, net.minecraft.util.EnumFacing.UP,
				((BlockCherrySapling) BlockInit.CHERRY_SAPLING));

		if (!isSoil) {
			return false;
		} else {
			int i = this.checkBlockLine(this.basePos, this.basePos.up(this.heightLimit - 1));

			if (i == -1) {
				return true;
			} else if (i < 6) {
				return false;
			} else {
				this.heightLimit = i;
				return true;
			}
		}
	}

	static class FoliageCoordinates extends BlockPos {
		private final int branchBase;

		public FoliageCoordinates(BlockPos pos, int size) {
			super(pos.getX(), pos.getY(), pos.getZ());
			this.branchBase = size;
		}

		public int getBranchBase() {
			return this.branchBase;
		}
	}
}