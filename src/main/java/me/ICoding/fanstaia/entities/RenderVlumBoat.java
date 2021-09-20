package me.ICoding.fanstaia.entities;

import me.ICoding.fanstaia.util.Reference;
import net.minecraft.client.renderer.entity.RenderBoat;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderVlumBoat extends RenderBoat
{
    private static final ResourceLocation BOAT_TEXTURE = new ResourceLocation (Reference.MOD_ID + ":textures/entity/boat/vlum_boat.png");
	public RenderVlumBoat(RenderManager manager) 
	{
		super(manager);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityBoat entity) 
	{
		return BOAT_TEXTURE;
	}
}
