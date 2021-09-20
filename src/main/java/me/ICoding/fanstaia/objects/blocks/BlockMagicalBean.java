package me.ICoding.fanstaia.objects.blocks;

import java.util.Random;

import me.ICoding.fanstaia.init.BlockInit;
import me.ICoding.fanstaia.init.ItemInit;
import me.ICoding.fanstaia.world.gen.features.WorldGenBeanstalk;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BlockMagicalBean extends BlockBush implements IGrowable
{
	public static final AxisAlignedBB MAGICAL_BEAN_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.8125D, 1.0D);
	
	public BlockMagicalBean(String name)
    {
    	setUnlocalizedName(name);
    	setRegistryName(name);
        this.setTickRandomly(true);
        
        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return MAGICAL_BEAN_AABB;
    }

	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if(rand.nextInt(20) == 0)
        {
        	for(int y = 0; y < rand.nextInt(60); y++)
        	{
        		this.grow(worldIn, rand, pos, state);
        	}
        }
    }
	
	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) 
	{
		return new ItemStack(ItemInit.MAGICAL_BEAN);
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) 
	{
		return ItemInit.MAGICAL_BEAN;
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) 
	{
		return new ItemStack(ItemInit.MAGICAL_BEAN);
	}

    /**
     * Checks if this block can be placed exactly at the given position.
     */
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        return super.canPlaceBlockAt(worldIn, pos) && this.canBlockStay(worldIn, pos, this.getDefaultState());
    }

    /**
     * Return true if the block can sustain a Bush
     */
    protected boolean canSustainBush(IBlockState state)
    {
        return state.isFullBlock();
    }

    public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state)
    {
        if (pos.getY() >= 0 && pos.getY() < 256)
        {
            IBlockState iblockstate = worldIn.getBlockState(pos.down());

            if (iblockstate.getBlock() == Blocks.GRASS /*&& worldIn.getBiome(pos) == BiomeInit.CHERRY*/)
            {
                return true;
            }
            else
            {
                return worldIn.getLight(pos) < 13 && iblockstate.getBlock().canSustainPlant(iblockstate, worldIn, pos.down(), net.minecraft.util.EnumFacing.UP, this);
            }
        }
        else
        {
            return false;
        }
    }

    public boolean generateBeanStalk(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        worldIn.setBlockToAir(pos);
        WorldGenerator worldgenerator = null;

        if (this == BlockInit.MAGICAL_BEAN_PLANT)
        {
            worldgenerator = new WorldGenBeanstalk();
        }

        if (worldgenerator != null && worldgenerator.generate(worldIn, rand, pos))
        {
            return true;
        }
        else
        {
            worldIn.setBlockState(pos, state, 3);
            return false;
        }
    }

    /**
     * Whether this IGrowable can grow
     */
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient)
    {
        return true;
    }

    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
    	//TODO: Change this to false
        return true;
    }

    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        this.generateBeanStalk(worldIn, pos, state, rand);
    }
}
