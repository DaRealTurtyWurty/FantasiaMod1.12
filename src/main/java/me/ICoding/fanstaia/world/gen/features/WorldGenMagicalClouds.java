package me.ICoding.fanstaia.world.gen.features;

import java.util.Random;

import me.ICoding.fanstaia.init.BlockInit;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenMagicalClouds extends WorldGenerator
{
	public boolean generate(World worldIn, Random rand, BlockPos position)
    {
        float f = (float)(rand.nextInt(1) + 3);

        for (int i = 0; f > 0.5F; --i)
        {
            for (int j = MathHelper.floor(-f); j <= MathHelper.ceil(f); ++j)
            {
                for (int k = MathHelper.floor(-f); k <= MathHelper.ceil(f); ++k)
                {
                    if ((float)(j * j + k * k) <= (f + 1.0F) * (f + 1.0F))
                    {
                        this.setBlockAndNotifyAdequately(worldIn, position.add(j, i, k), BlockInit.CLOUD_BLOCK.getDefaultState());
                    }
                }
            }

            f = (float)((double)f - ((double)rand.nextInt(1) + 0.5D));
        }

        return true;
    }
}
