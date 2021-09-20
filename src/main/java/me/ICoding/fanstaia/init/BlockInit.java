package me.ICoding.fanstaia.init;

import java.util.ArrayList;
import java.util.List;

import me.ICoding.fanstaia.Main;
import me.ICoding.fanstaia.objects.ItemBase;
import me.ICoding.fanstaia.objects.blocks.BlockBeanstalk;
import me.ICoding.fanstaia.objects.blocks.BlockBlueMoss;
import me.ICoding.fanstaia.objects.blocks.BlockCloud;
import me.ICoding.fanstaia.objects.blocks.BlockDoubleSlabBase;
import me.ICoding.fanstaia.objects.blocks.BlockFlowerBase;
import me.ICoding.fanstaia.objects.blocks.BlockGoldenEgg;
import me.ICoding.fanstaia.objects.blocks.BlockHalfSlabBase;
import me.ICoding.fanstaia.objects.blocks.BlockHopweed;
import me.ICoding.fanstaia.objects.blocks.BlockMagicalBean;
import me.ICoding.fanstaia.objects.blocks.cherry.BlockCherryBark;
import me.ICoding.fanstaia.objects.blocks.cherry.BlockCherryBookshelf;
import me.ICoding.fanstaia.objects.blocks.cherry.BlockCherryButton;
import me.ICoding.fanstaia.objects.blocks.cherry.BlockCherryDoor;
import me.ICoding.fanstaia.objects.blocks.cherry.BlockCherryFence;
import me.ICoding.fanstaia.objects.blocks.cherry.BlockCherryFenceGate;
import me.ICoding.fanstaia.objects.blocks.cherry.BlockCherryLadder;
import me.ICoding.fanstaia.objects.blocks.cherry.BlockCherryLeaves;
import me.ICoding.fanstaia.objects.blocks.cherry.BlockCherryLog;
import me.ICoding.fanstaia.objects.blocks.cherry.BlockCherryPlanks;
import me.ICoding.fanstaia.objects.blocks.cherry.BlockCherryPressurePlate;
import me.ICoding.fanstaia.objects.blocks.cherry.BlockCherrySapling;
import me.ICoding.fanstaia.objects.blocks.cherry.BlockCherryStairs;
import me.ICoding.fanstaia.objects.blocks.cherry.chest.BlockCherryChest;
import me.ICoding.fanstaia.objects.blocks.mystic_mushroom.BlockMysticMushroom;
import me.ICoding.fanstaia.objects.blocks.mystic_mushroom.MysticMushroomBlock;
import me.ICoding.fanstaia.objects.blocks.tree_structureblock.BlockTreeStructure;
import me.ICoding.fanstaia.objects.blocks.vlum.BlockVlumBark;
import me.ICoding.fanstaia.objects.blocks.vlum.BlockVlumBookshelf;
import me.ICoding.fanstaia.objects.blocks.vlum.BlockVlumButton;
import me.ICoding.fanstaia.objects.blocks.vlum.BlockVlumDoor;
import me.ICoding.fanstaia.objects.blocks.vlum.BlockVlumFence;
import me.ICoding.fanstaia.objects.blocks.vlum.BlockVlumFenceGate;
import me.ICoding.fanstaia.objects.blocks.vlum.BlockVlumLadder;
import me.ICoding.fanstaia.objects.blocks.vlum.BlockVlumLeaves;
import me.ICoding.fanstaia.objects.blocks.vlum.BlockVlumLog;
import me.ICoding.fanstaia.objects.blocks.vlum.BlockVlumPlanks;
import me.ICoding.fanstaia.objects.blocks.vlum.BlockVlumPressurePlate;
import me.ICoding.fanstaia.objects.blocks.vlum.BlockVlumSapling;
import me.ICoding.fanstaia.objects.blocks.vlum.BlockVlumStairs;
import me.ICoding.fanstaia.objects.blocks.vlum.chest.BlockVlumChest;
import me.ICoding.fanstaia.objects.blocks.vuzine.BlockVuzineBark;
import me.ICoding.fanstaia.objects.blocks.vuzine.BlockVuzineBookshelf;
import me.ICoding.fanstaia.objects.blocks.vuzine.BlockVuzineButton;
import me.ICoding.fanstaia.objects.blocks.vuzine.BlockVuzineDoor;
import me.ICoding.fanstaia.objects.blocks.vuzine.BlockVuzineFence;
import me.ICoding.fanstaia.objects.blocks.vuzine.BlockVuzineFenceGate;
import me.ICoding.fanstaia.objects.blocks.vuzine.BlockVuzineLadder;
import me.ICoding.fanstaia.objects.blocks.vuzine.BlockVuzineLeaves;
import me.ICoding.fanstaia.objects.blocks.vuzine.BlockVuzineLog;
import me.ICoding.fanstaia.objects.blocks.vuzine.BlockVuzinePlanks;
import me.ICoding.fanstaia.objects.blocks.vuzine.BlockVuzinePressurePlate;
import me.ICoding.fanstaia.objects.blocks.vuzine.BlockVuzineSapling;
import me.ICoding.fanstaia.objects.blocks.vuzine.BlockVuzineStairs;
import me.ICoding.fanstaia.objects.blocks.vuzine.chest.BlockVuzineChest;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class BlockInit 
{
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	public static final Material CLOUD = (new Material(MapColor.AIR));
	
	/*Cherry Wood Blocks*/
	public static final Block CHERRY_LOG = new BlockCherryLog("cherry_log");
	public static final Block CHERRY_BARK = new BlockCherryBark("cherry_bark");
	public static final Block PINK_CHERRY_LEAVES = new BlockCherryLeaves("cherry_leaves");
	public static final Block WHITE_CHERRY_LEAVES = new BlockCherryLeaves("white_cherry_leaves");
	public static final Block CHERRY_PLANKS = new BlockCherryPlanks("cherry_planks");
	public static final Block CHERRY_BOOKSHELF = new BlockCherryBookshelf("cherry_bookshelf");
	public static final Block CHERRY_BUTTON = new BlockCherryButton("cherry_button");
	public static final Block CHERRY_STAIRS = new BlockCherryStairs("cherry_stairs", BlockInit.CHERRY_PLANKS.getDefaultState());
	public static final Block CHERRY_PRESSURE_PLATE = new BlockCherryPressurePlate("cherry_pressure_plate");
	public static final Block CHERRY_FENCE = new BlockCherryFence("cherry_fence");
	public static final Block CHERRY_FENCE_GATE = new BlockCherryFenceGate("cherry_fence_gate");
	public static final Block CHERRY_LADDER = new BlockCherryLadder("cherry_ladder");
	public static final Block CHERRY_SAPLING = new BlockCherrySapling("cherry_sapling");
	public static final Block CHERRY_DOOR = new BlockCherryDoor("cherry_door");
	public static final BlockSlab CHERRY_SLAB_DOUBLE = new BlockDoubleSlabBase("cherry_slab_double", Material.WOOD, Main.MAIN_TAB, BlockInit.CHERRY_SLAB_HALF);
	public static final BlockSlab CHERRY_SLAB_HALF = new BlockHalfSlabBase("cherry_slab_half", Material.WOOD, Main.MAIN_TAB, BlockInit.CHERRY_SLAB_HALF, BlockInit.CHERRY_SLAB_DOUBLE);
	public static final Block CHERRY_CHEST = new BlockCherryChest("cherry_chest", BlockCherryChest.Type.BASIC);		
	
	/*Vlum Wood Blocks*/
	public static final Block VLUM_LOG = new BlockVlumLog("vlum_log");
	public static final Block VLUM_BARK = new BlockVlumBark("vlum_bark");
	public static final Block VLUM_LEAVES = new BlockVlumLeaves("vlum_leaves");
	public static final Block VLUM_PLANKS = new BlockVlumPlanks("vlum_planks");
	public static final Block VLUM_BOOKSHELF = new BlockVlumBookshelf("vlum_bookshelf");
	public static final Block VLUM_BUTTON = new BlockVlumButton("vlum_button");
	public static final Block VLUM_STAIRS = new BlockVlumStairs("vlum_stairs", BlockInit.VLUM_PLANKS.getDefaultState());
	public static final Block VLUM_PRESSURE_PLATE = new BlockVlumPressurePlate("vlum_pressure_plate");
	public static final Block VLUM_FENCE = new BlockVlumFence("vlum_fence");
	public static final Block VLUM_FENCE_GATE = new BlockVlumFenceGate("vlum_fence_gate");
	public static final Block VLUM_LADDER = new BlockVlumLadder("vlum_ladder");
	public static final Block VLUM_SAPLING = new BlockVlumSapling("vlum_sapling");
	public static final Block VLUM_DOOR = new BlockVlumDoor("vlum_door");
	public static final BlockSlab VLUM_SLAB_DOUBLE = new BlockDoubleSlabBase("vlum_slab_double", Material.WOOD, Main.MAIN_TAB, BlockInit.VLUM_SLAB_HALF);
	public static final BlockSlab VLUM_SLAB_HALF = new BlockHalfSlabBase("vlum_slab_half", Material.WOOD, Main.MAIN_TAB, BlockInit.VLUM_SLAB_HALF, BlockInit.VLUM_SLAB_DOUBLE);
	public static final Block VLUM_CHEST = new BlockVlumChest("vlum_chest", BlockVlumChest.Type.BASIC);
	
	/*Vuzine Wood Blocks*/
	public static final Block VUZINE_LOG = new BlockVuzineLog("vuzine_log");
	public static final Block VUZINE_BARK = new BlockVuzineBark("vuzine_bark");
	public static final Block VUZINE_LEAVES = new BlockVuzineLeaves("vuzine_leaves");
	public static final Block VUZINE_PLANKS = new BlockVuzinePlanks("vuzine_planks");
	public static final Block VUZINE_BOOKSHELF = new BlockVuzineBookshelf("vuzine_bookshelf");
	public static final Block VUZINE_BUTTON = new BlockVuzineButton("vuzine_button");
	public static final Block VUZINE_STAIRS = new BlockVuzineStairs("vuzine_stairs", BlockInit.VUZINE_PLANKS.getDefaultState());
	public static final Block VUZINE_PRESSURE_PLATE = new BlockVuzinePressurePlate("vuzine_pressure_plate");
	public static final Block VUZINE_FENCE = new BlockVuzineFence("vuzine_fence");
	public static final Block VUZINE_FENCE_GATE = new BlockVuzineFenceGate("vuzine_fence_gate");
	public static final Block VUZINE_LADDER = new BlockVuzineLadder("vuzine_ladder");
	public static final Block VUZINE_SAPLING = new BlockVuzineSapling("vuzine_sapling");
	public static final Block VUZINE_DOOR = new BlockVuzineDoor("vuzine_door");
	public static final BlockSlab VUZINE_SLAB_DOUBLE = new BlockDoubleSlabBase("vuzine_slab_double", Material.WOOD, Main.MAIN_TAB, BlockInit.VUZINE_SLAB_HALF);
	public static final BlockSlab VUZINE_SLAB_HALF = new BlockHalfSlabBase("vuzine_slab_half", Material.WOOD, Main.MAIN_TAB, BlockInit.VUZINE_SLAB_HALF, BlockInit.VUZINE_SLAB_DOUBLE);
	public static final Block VUZINE_CHEST = new BlockVuzineChest("vuzine_chest", BlockVuzineChest.Type.BASIC);
	
	/*Testing Blocks*/
	public static final Block TEST_PLANKS = new BlockVuzinePlanks("test_planks");
	public static final Block TEST_STAIRS = new BlockVuzineStairs("test_stairs", BlockInit.TEST_PLANKS.getDefaultState());
	public static final Item TEST_ITEM = new ItemBase("test_item");
	public static final BlockSlab TEST_SLAB_DOUBLE = new BlockDoubleSlabBase("test_slab_double", Material.ANVIL, Main.MAIN_TAB, BlockInit.TEST_SLAB_HALF);
	public static final BlockSlab TEST_SLAB_HALF = new BlockHalfSlabBase("test_slab_half", Material.ANVIL, Main.MAIN_TAB, BlockInit.TEST_SLAB_HALF, BlockInit.TEST_SLAB_DOUBLE);
	public static final Block TEST_FENCE = new BlockVuzineFence("test_fence");
	public static final Block TEST_FENCE_GATE = new BlockVuzineFenceGate("test_fence_gate");
	
	/*Other*/
	public static final Block TREE_STRUCTURE_BLOCK = new BlockTreeStructure("tree_structure");
	public static final Block MYSTIC_MUSHROOM = new BlockMysticMushroom("mystic_mushroom");
	public static final Block MYSTIC_MUSHROOM_BLOCK = new MysticMushroomBlock("mushroom_mystic");
	public static final Block NEPETA_FLOWER = new BlockFlowerBase("nepeta");
	public static final Block MAGICAL_BEAN_PLANT = new BlockMagicalBean("magical_bean_plant");
	public static final Block BEANSTALK_BLOCK = new BlockBeanstalk("beanstalk_block");
	public static final Block GOLDEN_EGG = new BlockGoldenEgg("golden_egg");
	public static final Block CLOUD_BLOCK = new BlockCloud("cloud_block");
	public static final Block CLOUD_PURPLE = new BlockCloud("cloud_purple");
	public static final Block CLOUD_PINK = new BlockCloud("cloud_pink");
	public static final Block CLOUD_CYAN = new BlockCloud("cloud_cyan");
	public static final Block CLOUD_YELLOW = new BlockCloud("cloud_yellow");
	public static final Block CLOUD_LIME = new BlockCloud("cloud_lime");
	public static final Block CLOUD_RED = new BlockCloud("cloud_red");
	public static final Block BLUE_MOSS = new BlockBlueMoss("blue_moss");
	public static final Block HOPWEED = new BlockHopweed("hopweed");
}