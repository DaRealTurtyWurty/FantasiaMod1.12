package me.ICoding.fanstaia.objects.blocks.vlum.chest;

import javax.annotation.Nullable;

import me.ICoding.fanstaia.util.Reference;
import me.ICoding.fanstaia.util.VlumDoubleChestHandler;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.ItemStackDataLists;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class TileEntityVlumChest extends TileEntityLockableLoot implements ITickable
{
    private NonNullList<ItemStack> chestContents = NonNullList.<ItemStack>withSize(27, ItemStack.EMPTY);
    /** Determines if the check for adjacent chests has taken place. */
    public boolean adjacentChestChecked;
    /** Contains the chest tile located adjacent to this one (if any) */
    public TileEntityVlumChest adjacentChestZNeg;
    /** Contains the chest tile located adjacent to this one (if any) */
    public TileEntityVlumChest adjacentChestXPos;
    /** Contains the chest tile located adjacent to this one (if any) */
    public TileEntityVlumChest adjacentChestXNeg;
    /** Contains the chest tile located adjacent to this one (if any) */
    public TileEntityVlumChest adjacentChestZPos;
    /** The current angle of the lid (between 0 and 1) */
    public float lidAngle;
    /** The angle of the lid last tick */
    public float prevLidAngle;
    /** The number of players currently using this chest */
    public int numPlayersUsing;
    /** Server sync counter (once per 20 ticks) */
    private int ticksSinceSync;
    private BlockVlumChest.Type cachedChestType;

    public TileEntityVlumChest()
    {
    }

    public TileEntityVlumChest(BlockVlumChest.Type typeIn)
    {
        this.cachedChestType = typeIn;
    }

    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory()
    {
        return 27;
    }

    public boolean isEmpty()
    {
        for (ItemStack itemstack : this.chestContents)
        {
            if (!itemstack.isEmpty())
            {
                return false;
            }
        }

        return true;
    }

    /**
     * Get the name of this object. For players this returns their username
     */
    public String getName()
    {
        return this.hasCustomName() ? this.customName : "container.vlum_chest";
    }

    public static void registerFixesChest(DataFixer fixer)
    {
        fixer.registerWalker(FixTypes.BLOCK_ENTITY, new ItemStackDataLists(TileEntityVlumChest.class, new String[] {"Items"}));
    }

    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.chestContents = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);

        if (!this.checkLootAndRead(compound))
        {
            ItemStackHelper.loadAllItems(compound, this.chestContents);
        }

        if (compound.hasKey("CustomName", 8))
        {
            this.customName = compound.getString("CustomName");
        }
    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);

        if (!this.checkLootAndWrite(compound))
        {
            ItemStackHelper.saveAllItems(compound, this.chestContents);
        }

        if (this.hasCustomName())
        {
            compound.setString("CustomName", this.customName);
        }

        return compound;
    }

    /**
     * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended.
     */
    public int getInventoryStackLimit()
    {
        return 64;
    }

    public void updateContainingBlockInfo()
    {
        super.updateContainingBlockInfo();
        this.adjacentChestChecked = false;
        doubleChestHandler = null;
    }

    @SuppressWarnings("incomplete-switch")
    private void setNeighbor(TileEntityVlumChest chestTe, EnumFacing side)
    {
        if (chestTe.isInvalid())
        {
            this.adjacentChestChecked = false;
        }
        else if (this.adjacentChestChecked)
        {
            switch (side)
            {
                case NORTH:

                    if (this.adjacentChestZNeg != chestTe)
                    {
                        this.adjacentChestChecked = false;
                    }

                    break;
                case SOUTH:

                    if (this.adjacentChestZPos != chestTe)
                    {
                        this.adjacentChestChecked = false;
                    }

                    break;
                case EAST:

                    if (this.adjacentChestXPos != chestTe)
                    {
                        this.adjacentChestChecked = false;
                    }

                    break;
                case WEST:

                    if (this.adjacentChestXNeg != chestTe)
                    {
                        this.adjacentChestChecked = false;
                    }
            }
        }
    }

    /**
     * Performs the check for adjacent chests to determine if this chest is double or not.
     */
    public void checkForAdjacentChests()
    {
        if (!this.adjacentChestChecked)
        {
            if (this.world == null || !this.world.isAreaLoaded(this.pos, 1)) return; // Forge: prevent loading unloaded chunks when checking neighbors
            this.adjacentChestChecked = true;
            this.adjacentChestXNeg = this.getAdjacentChest(EnumFacing.WEST);
            this.adjacentChestXPos = this.getAdjacentChest(EnumFacing.EAST);
            this.adjacentChestZNeg = this.getAdjacentChest(EnumFacing.NORTH);
            this.adjacentChestZPos = this.getAdjacentChest(EnumFacing.SOUTH);
        }
    }

    @Nullable
    protected TileEntityVlumChest getAdjacentChest(EnumFacing side)
    {
        BlockPos blockpos = this.pos.offset(side);

        if (this.isChestAt(blockpos))
        {
            TileEntity tileentity = this.world.getTileEntity(blockpos);

            if (tileentity instanceof TileEntityVlumChest)
            {
                TileEntityVlumChest TileEntityVlumChest = (TileEntityVlumChest)tileentity;
                TileEntityVlumChest.setNeighbor(this, side.getOpposite());
                return TileEntityVlumChest;
            }
        }

        return null;
    }

    private boolean isChestAt(BlockPos posIn)
    {
        if (this.world == null)
        {
            return false;
        }
        else
        {
            Block block = this.world.getBlockState(posIn).getBlock();
            return block instanceof BlockVlumChest && ((BlockVlumChest)block).chestType == this.getChestType();
        }
    }

    /**
     * Like the old updateEntity(), except more generic.
     */
    @SuppressWarnings("unused")
	public void update()
    {
        this.checkForAdjacentChests();
        int i = this.pos.getX();
        int j = this.pos.getY();
        int k = this.pos.getZ();
        ++this.ticksSinceSync;

        if (!this.world.isRemote && this.numPlayersUsing != 0 && (this.ticksSinceSync + i + j + k) % 200 == 0)
        {
            this.numPlayersUsing = 0;
            float f = 5.0F;

            for (EntityPlayer entityplayer : this.world.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB((double)((float)i - 5.0F), (double)((float)j - 5.0F), (double)((float)k - 5.0F), (double)((float)(i + 1) + 5.0F), (double)((float)(j + 1) + 5.0F), (double)((float)(k + 1) + 5.0F))))
            {
                if (entityplayer.openContainer instanceof ContainerVlumChest)
                {
                    IInventory iinventory = ((ContainerVlumChest)entityplayer.openContainer).getLowerChestInventory();

                    if (iinventory == this || iinventory instanceof InventoryLargeChest && ((InventoryLargeChest)iinventory).isPartOfLargeChest(this))
                    {
                        ++this.numPlayersUsing;
                    }
                }
            }
        }

        this.prevLidAngle = this.lidAngle;
		float f1 = 0.1F;

        if (this.numPlayersUsing > 0 && this.lidAngle == 0.0F && this.adjacentChestZNeg == null && this.adjacentChestXNeg == null)
        {
            double d1 = (double)i + 0.5D;
            double d2 = (double)k + 0.5D;

            if (this.adjacentChestZPos != null)
            {
                d2 += 0.5D;
            }

            if (this.adjacentChestXPos != null)
            {
                d1 += 0.5D;
            }

            this.world.playSound((EntityPlayer)null, d1, (double)j + 0.5D, d2, SoundEvents.BLOCK_CHEST_OPEN, SoundCategory.BLOCKS, 0.5F, this.world.rand.nextFloat() * 0.1F + 0.9F);
        }

        if (this.numPlayersUsing == 0 && this.lidAngle > 0.0F || this.numPlayersUsing > 0 && this.lidAngle < 1.0F)
        {
            float f2 = this.lidAngle;

            if (this.numPlayersUsing > 0)
            {
                this.lidAngle += 0.1F;
            }
            else
            {
                this.lidAngle -= 0.1F;
            }

            if (this.lidAngle > 1.0F)
            {
                this.lidAngle = 1.0F;
            }

            float f3 = 0.5F;

            if (this.lidAngle < 0.5F && f2 >= 0.5F && this.adjacentChestZNeg == null && this.adjacentChestXNeg == null)
            {
                double d3 = (double)i + 0.5D;
                double d0 = (double)k + 0.5D;

                if (this.adjacentChestZPos != null)
                {
                    d0 += 0.5D;
                }

                if (this.adjacentChestXPos != null)
                {
                    d3 += 0.5D;
                }

                this.world.playSound((EntityPlayer)null, d3, (double)j + 0.5D, d0, SoundEvents.BLOCK_CHEST_CLOSE, SoundCategory.BLOCKS, 0.5F, this.world.rand.nextFloat() * 0.1F + 0.9F);
            }

            if (this.lidAngle < 0.0F)
            {
                this.lidAngle = 0.0F;
            }
        }
    }

    public boolean receiveClientEvent(int id, int type)
    {
        if (id == 1)
        {
            this.numPlayersUsing = type;
            return true;
        }
        else
        {
            return super.receiveClientEvent(id, type);
        }
    }

    public void openInventory(EntityPlayer player)
    {
        if (!player.isSpectator())
        {
            if (this.numPlayersUsing < 0)
            {
                this.numPlayersUsing = 0;
            }

            ++this.numPlayersUsing;
            this.world.addBlockEvent(this.pos, this.getBlockType(), 1, this.numPlayersUsing);
            this.world.notifyNeighborsOfStateChange(this.pos, this.getBlockType(), false);
        }
    }

    public void closeInventory(EntityPlayer player)
    {
        if (!player.isSpectator() && this.getBlockType() instanceof BlockVlumChest)
        {
            --this.numPlayersUsing;
            this.world.addBlockEvent(this.pos, this.getBlockType(), 1, this.numPlayersUsing);
            this.world.notifyNeighborsOfStateChange(this.pos, this.getBlockType(), false);
        }
    }

    public VlumDoubleChestHandler doubleChestHandler;

    @SuppressWarnings("unchecked")
    @Override
    @Nullable
    public <T> T getCapability(net.minecraftforge.common.capabilities.Capability<T> capability, @Nullable net.minecraft.util.EnumFacing facing)
    {
        if (capability == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
        {
            if(doubleChestHandler == null || doubleChestHandler.needsRefresh())
                doubleChestHandler = VlumDoubleChestHandler.get(this);
            if (doubleChestHandler != null && doubleChestHandler != VlumDoubleChestHandler.NO_ADJACENT_CHESTS_INSTANCE)
                return (T) doubleChestHandler;
        }
        return super.getCapability(capability, facing);
    }

    public net.minecraftforge.items.IItemHandler getSingleChestHandler()
    {
        return super.getCapability(net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
    }


    /**
     * invalidates a tile entity
     */
    public void invalidate()
    {
        super.invalidate();
        this.updateContainingBlockInfo();
        this.checkForAdjacentChests();
    }

    public BlockVlumChest.Type getChestType()
    {
        if (this.cachedChestType == null)
        {
            if (this.world == null || !(this.getBlockType() instanceof BlockVlumChest))
            {
                return BlockVlumChest.Type.BASIC;
            }

            this.cachedChestType = ((BlockVlumChest)this.getBlockType()).chestType;
        }

        return this.cachedChestType;
    }

    public String getGuiID()
    {
        return Reference.MOD_ID + ":vlum_chest";
    }

    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
    {
        this.fillWithLoot(playerIn);
        return new ContainerVlumChest(playerInventory, this, playerIn);
    }

    protected NonNullList<ItemStack> getItems()
    {
        return this.chestContents;
    }
}
