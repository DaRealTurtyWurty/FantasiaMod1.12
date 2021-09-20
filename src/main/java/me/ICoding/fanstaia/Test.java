package me.ICoding.fanstaia;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Test extends TileEntity implements ITickable {

	private int energy = 0;
	private List<String> currentFacing = new ArrayList<String>();

	@Override
	public void update() {
		boolean dirty = false;
		if (this.world != null && !this.world.isRemote) {
			for (EnumFacing facing : EnumFacing.VALUES) {
				if (this.world.getBlockState(pos.offset(facing.getOpposite()))
						.getBlock() == ModBlocks.FLUX_PIPE_BLOCK) {
					TileEntityFluxPipe fluxPipe = (TileEntityFluxPipe) this.world
							.getTileEntity(this.pos.offset(facing.getOpposite()));
					if (!currentFacing.contains(facing.getOpposite().getName())
							&& fluxPipe.getCurrentFacing().contains(facing.getOpposite().getName())) {
						if (this.world.getBlockState(pos.offset(facing)).getBlock() == ModBlocks.FLUX_PIPE_BLOCK) {
							TileEntityFluxPipe te = (TileEntityFluxPipe) this.world
									.getTileEntity(this.pos.offset(facing));
							if (this.getFuelValueFromPipe() > 0) {
								if (this.getFuelValueFromPipe() < 100) {
									this.consumeEnergy(Math.min(1, this.energy));
									te.energy += 1;
									this.currentFacing.add(facing.getName());
									dirty = true;
								}
							}
						} else if (this.world.getBlockState(this.pos.offset(facing))
								.getBlock() == ModBlocks.FLUX_GENERATOR_BLOCK) {
							TileEntityFluxGenerator te = (TileEntityFluxGenerator) this.world
									.getTileEntity(pos.offset(facing));
							if (BlockEntityFluxPipe.isSideConnectable1(this.world, this.pos, facing)) {
								if (te.getFuelValueFromGenerator() > 0) {
									if (te.getFuelValueFromGenerator() <= 1000) {
										te.consumeEnergy(Math.min(1, te.energy));
										this.energy += 1;
									} else {
										te.energy = 1000;
									}
									this.currentFacing.remove(facing.getName());
									this.currentFacing.add(facing.getOpposite().getName());
									dirty = true;
								}
							}
						}

						else if (this.world.getBlockState(this.pos.offset(facing))
								.getBlock() == ModBlocks.FLUX_STORAGE_BLOCK) {
							TileEntityFluxStorage te1 = (TileEntityFluxStorage) this.world
									.getTileEntity(this.pos.offset(facing));
							if (BlockEntityFluxPipe.isSideConnectable1(this.world, this.pos, facing)) {
								if (this.getFuelValueFromPipe() > 0) {
									if (this.getFuelValueFromPipe() < 100) {
										if (te1.getFuelValueFromStorage() < 1000) {
											te1.addEnergy(1);
											this.energy -= 1;
											dirty = true;
										}
									}
								}
							}
						}
					}
				} else {
					if (!currentFacing.contains(facing.getOpposite().getName())) {
						if (this.world.getBlockState(pos.offset(facing)).getBlock() == ModBlocks.FLUX_PIPE_BLOCK) {
							TileEntityFluxPipe te = (TileEntityFluxPipe) this.world
									.getTileEntity(this.pos.offset(facing));
							if (this.getFuelValueFromPipe() > 0) {
								if (this.getFuelValueFromPipe() < 100) {
									this.consumeEnergy(Math.min(1, this.energy));
									te.energy += 1;
									this.currentFacing.add(facing.getName());
									dirty = true;
								}
							}
						} else if (this.world.getBlockState(this.pos.offset(facing))
								.getBlock() == ModBlocks.FLUX_GENERATOR_BLOCK) {
							TileEntityFluxGenerator te = (TileEntityFluxGenerator) this.world
									.getTileEntity(pos.offset(facing));
							if (BlockEntityFluxPipe.isSideConnectable1(this.world, this.pos, facing)) {
								if (te.getFuelValueFromGenerator() > 0) {
									if (te.getFuelValueFromGenerator() <= 1000) {
										te.consumeEnergy(Math.min(1, te.energy));
										this.energy += 1;
									} else {
										te.energy = 1000;
									}
									this.currentFacing.remove(facing.getName());
									this.currentFacing.add(facing.getOpposite().getName());
									dirty = true;
								}
							}
						}

						else if (this.world.getBlockState(this.pos.offset(facing))
								.getBlock() == ModBlocks.FLUX_STORAGE_BLOCK) {
							TileEntityFluxStorage te1 = (TileEntityFluxStorage) this.world
									.getTileEntity(this.pos.offset(facing));
							if (BlockEntityFluxPipe.isSideConnectable1(this.world, this.pos, facing)) {
								if (this.getFuelValueFromPipe() > 0) {
									if (this.getFuelValueFromPipe() < 100) {
										if (te1.getFuelValueFromStorage() < 1000) {
											te1.addEnergy(1);
											this.energy -= 1;
											dirty = true;
										}
									}
								}
							}
						}
					}
				}
			}
		}

		if (dirty) {
			this.markDirty();
			this.world.notifyBlockUpdate(this.pos, this.world.getBlockState(this.pos),
					this.world.getBlockState(this.pos), 3);
		}
	}

	public List<String> getCurrentFacing() {
		return this.currentFacing;
	}

	public int getFuelValueFromPipe() {
		return 5;
	}

	public void consumeEnergy(int amount) {

	}

	public class TileEntityFluxGenerator extends TileEntity {

		private int energy;

		public void consumeEnergy(int amount) {

		}

		public int getFuelValueFromGenerator() {
			return 5;
		}
	}

	public static class BlockEntityFluxPipe extends Block {

		public BlockEntityFluxPipe() {
			super(Material.DRAGON_EGG);
		}

		public static boolean isSideConnectable1(World world, BlockPos pos, EnumFacing facing) {
			return true;
		}
	}

	public static class ModBlocks {
		public static Block FLUX_GENERATOR_BLOCK;
		public static Block FLUX_PIPE_BLOCK;
		public static Block FLUX_STORAGE_BLOCK;
	}

	public class TileEntityFluxPipe extends TileEntity {

		private int energy;
		private List<String> currentFacing = new ArrayList<String>();

		public List<String> getCurrentFacing() {
			return this.currentFacing;
		}
	}

	public class TileEntityFluxStorage extends TileEntity {
		public int getFuelValueFromStorage() {
			return 60;
		}

		public void addEnergy(int amount) {

		}
	}
}
