package me.ICoding.fanstaia.worlddata;

import java.util.ArrayList;
import java.util.List;

import me.ICoding.fanstaia.util.Reference;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldSavedData;

public class StructureSpawning extends WorldSavedData {

	private static final String IDENTIFIER = Reference.MOD_ID + "_WORLDDATA";

	private NBTTagCompound data = new NBTTagCompound();
	private int beanstalkCount = 0;
	private List<Integer> pos = new ArrayList<>();
	private int posX = 0;
	private int posY = 0;
	private int posZ = 0;

	public StructureSpawning(String s) {
		super(s);
	}

	public StructureSpawning() {
		super(IDENTIFIER);

	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		beanstalkCount = nbt.getInteger("count");
		posX = nbt.getInteger("posX");
		posY = nbt.getInteger("posY");
		posZ = nbt.getInteger("posZ");

	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		nbt.setInteger("count", this.beanstalkCount);
		nbt.setInteger("posX", posX);
		nbt.setInteger("posY", posY);
		nbt.setInteger("posZ", posZ);
		return nbt;
	}

	public static StructureSpawning get(World world) {
		StructureSpawning save = (StructureSpawning) world.getMapStorage().getOrLoadData(StructureSpawning.class,
				IDENTIFIER);

		if (save == null) {
			save = new StructureSpawning();
			world.getMapStorage().setData(IDENTIFIER, save);

		}
		return save;
	}

	public void setBeanstalkCount(int value) {
		this.beanstalkCount = value;
		this.markDirty();
	}

	public int getBeanstalkCount() {
		return this.beanstalkCount;
	}

	public void setPos(int x, int y, int z) {
		this.posX = x;
		this.posY = y;
		this.posZ = z;

		this.markDirty();

	}

	public List<Integer> getPos() {
		pos.clear();
		pos.add(posX);
		pos.add(posY);
		pos.add(posZ);
		return pos;
	}

}
