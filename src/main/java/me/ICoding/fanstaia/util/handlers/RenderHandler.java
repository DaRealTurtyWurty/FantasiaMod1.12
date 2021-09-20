package me.ICoding.fanstaia.util.handlers;

import me.ICoding.fanstaia.entities.EntityCherryBoat;
import me.ICoding.fanstaia.entities.EntityVlumBoat;
import me.ICoding.fanstaia.entities.EntityVuzineBoat;
import me.ICoding.fanstaia.entities.RenderCherryBoat;
import me.ICoding.fanstaia.entities.RenderVlumBoat;
import me.ICoding.fanstaia.entities.RenderVuzineBoat;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler 
{
	public static void registerEntityRenderers()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityCherryBoat.class, new IRenderFactory<EntityCherryBoat>() 
		{
			@Override
			public Render<? super EntityCherryBoat> createRenderFor(RenderManager manager) 
			{
				return new RenderCherryBoat(manager);
			}
		});
		
		RenderingRegistry.registerEntityRenderingHandler(EntityVlumBoat.class, new IRenderFactory<EntityVlumBoat>() 
		{
			@Override
			public Render<? super EntityVlumBoat> createRenderFor(RenderManager manager) 
			{
				return new RenderVlumBoat(manager);
			}
		});
		
		RenderingRegistry.registerEntityRenderingHandler(EntityVuzineBoat.class, new IRenderFactory<EntityVuzineBoat>() 
		{
			@Override
			public Render<? super EntityVuzineBoat> createRenderFor(RenderManager manager) 
			{
				return new RenderVuzineBoat(manager);
			}
		});
	}
}
