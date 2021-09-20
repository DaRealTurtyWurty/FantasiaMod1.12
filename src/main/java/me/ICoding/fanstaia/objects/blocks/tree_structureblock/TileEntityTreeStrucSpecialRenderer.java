package me.ICoding.fanstaia.objects.blocks.tree_structureblock;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public class TileEntityTreeStrucSpecialRenderer extends TileEntitySpecialRenderer<TileEntityTreeStructureBlock>
{
	@Override
	public void render(TileEntityTreeStructureBlock te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) 
	{
		//if(te.shouldBoxRender() == true)
		//{
			//System.out.println("TESR!");
			GlStateManager.disableFog();
	        GlStateManager.disableLighting();
	        GlStateManager.disableTexture2D();
	        GlStateManager.enableBlend();
	        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
	        this.setLightmapDisabled(true);
			RenderGlobal.drawBoundingBox(x-1, y-3, z-1, x+2, y+2, z+2, 1.0f, 1.0f, 1.0f, 1.0f);
			this.setLightmapDisabled(false);
	        GlStateManager.glLineWidth(2.0F);
	        GlStateManager.enableLighting();
	        GlStateManager.enableTexture2D();
	        GlStateManager.enableDepth();
	        GlStateManager.depthMask(true);
	        GlStateManager.enableFog();
		//}
	}
}
