package me.ICoding.fanstaia.init;

import me.ICoding.fanstaia.world.gen.biomes.CherryGrove;
import me.ICoding.fanstaia.world.gen.biomes.MagicalMeadows;
import me.ICoding.fanstaia.world.gen.biomes.VlumWoods;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
//import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class BiomeInit 
{
	public static final Biome CHERRY_GROVE = new CherryGrove();
	public static final Biome VLUM_WOODS = new VlumWoods();
	public static final Biome MAGICAL_MEADOWS = new MagicalMeadows();
	
	public static void registerBiomes() 
	{
		initBiome(CHERRY_GROVE, "Cherry Grove", BiomeType.COOL, Type.LUSH, Type.MAGICAL, Type.FOREST, Type.DENSE);
		initBiome(VLUM_WOODS, "Vlum Woods", BiomeType.COOL, Type.LUSH, Type.MAGICAL, Type.FOREST, Type.DENSE);
		initBiome(MAGICAL_MEADOWS, "Magical Meadows", BiomeType.COOL, Type.LUSH, Type.MAGICAL, Type.PLAINS, Type.SPARSE);
	}
	
	private static Biome initBiome(Biome biome, String name, BiomeType bType, Type...types) 
	{
		biome.setRegistryName(name);
        ForgeRegistries.BIOMES.register(biome);
        BiomeDictionary.addTypes(biome, types);
        BiomeManager.addSpawnBiome(biome);
        //BiomeManager.addBiome(bType, new BiomeEntry(biome, 10));
        return biome;
	}
	
}
