package me.ICoding.fanstaia.util;

import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class CustomMaterial extends Material
{
	public static final CustomMaterial CLOUD = (new CustomMaterial(MapColor.AIR)).setRequiresTool();
	/** Bool defining if the block can burn or not. */
    private boolean canBurn;
    /**
     * Determines whether blocks with this material can be "overwritten" by other blocks when placed - eg snow, vines
     * and tall grass.
     */
    private boolean replaceable;
    /** Indicates if the material is translucent */
    private boolean isTranslucent;
    /** The color index used to draw the blocks of this material on maps. */
    private final MapColor materialMapColor;
    /** Determines if the material can be harvested without a tool (or with the wrong tool) */
    private boolean requiresNoTool = true;
    /**
     * Mobility information flag. 0 indicates that this block is normal, 1 indicates that it can't push other blocks, 2
     * indicates that it can't be pushed.
     */
    private EnumPushReaction mobilityFlag = EnumPushReaction.NORMAL;
	private boolean isAdventureModeExempt;
  
	public CustomMaterial(MapColor color)
    {
		super(color);
        this.materialMapColor = color;
    }
	
	/**
     * Returns if blocks of these materials are liquids.
     */
    public boolean isLiquid()
    {
        return false;
    }

    /**
     * Returns true if the block is a considered solid. This is true by default.
     */
    public boolean isSolid()
    {
        return true;
    }

    /**
     * Will prevent grass from growing on dirt underneath and kill any grass below it if it returns true
     */
    public boolean blocksLight()
    {
        return true;
    }

    /**
     * Returns if this material is considered solid or not
     */
    public boolean blocksMovement()
    {
        return true;
    }

    /**
     * Marks the material as translucent
     */
	private CustomMaterial setTranslucent()
    {
        this.isTranslucent = true;
        return this;
    }

    /**
     * Makes blocks with this material require the correct tool to be harvested.
     */
    protected CustomMaterial setRequiresTool()
    {
        this.requiresNoTool = false;
        return this;
    }

    /**
     * Set the canBurn bool to True and return the current object.
     */
    protected CustomMaterial setBurning()
    {
        this.canBurn = true;
        return this;
    }

    /**
     * Returns if the block can burn or not.
     */
    public boolean getCanBurn()
    {
        return this.canBurn;
    }

    /**
     * Sets {@link #replaceable} to true.
     */
    public CustomMaterial setReplaceable()
    {
        this.replaceable = true;
        return this;
    }

    /**
     * Returns whether the material can be replaced by other blocks when placed - eg snow, vines and tall grass.
     */
    public boolean isReplaceable()
    {
        return this.replaceable;
    }

    /**
     * Indicate if the material is opaque
     */
    public boolean isOpaque()
    {
        return this.isTranslucent ? false : this.blocksMovement();
    }

    /**
     * Returns true if the material can be harvested without a tool (or with the wrong tool)
     */
    public boolean isToolNotRequired()
    {
        return this.requiresNoTool;
    }

    public EnumPushReaction getMobilityFlag()
    {
        return this.mobilityFlag;
    }

    /**
     * This type of material can't be pushed, but pistons can move over it.
     */
    protected CustomMaterial setNoPushMobility()
    {
        this.mobilityFlag = EnumPushReaction.DESTROY;
        return this;
    }

    /**
     * This type of material can't be pushed, and pistons are blocked to move.
     */
    protected CustomMaterial setImmovableMobility()
    {
        this.mobilityFlag = EnumPushReaction.BLOCK;
        return this;
    }

    /**
     * @see #isAdventureModeExempt()
     */
    protected CustomMaterial setAdventureModeExempt()
    {
        this.isAdventureModeExempt = true;
        return this;
    }

    /**
     * Retrieves the color index of the block. This is is the same color used by vanilla maps to represent this block.
     */
    public MapColor getMaterialMapColor()
    {
        return this.materialMapColor;
    }
}
