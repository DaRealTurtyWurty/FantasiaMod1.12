package me.ICoding.fanstaia.entities;

import net.minecraft.client.model.IMultipassModel;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelVuzineBoat extends ModelBase implements IMultipassModel
{
    public ModelRenderer[] boatSides = new ModelRenderer[5];
    public ModelRenderer[] paddles = new ModelRenderer[2];
    
    /**
     * An invisible layer that is rendered to make it seem like there's no water in the boat.
     */
    public ModelRenderer noWater;

    public ModelVuzineBoat()
    {
        this.boatSides[0] = (new ModelRenderer(this, 0, 0)).setTextureSize(128, 64);
        this.boatSides[1] = (new ModelRenderer(this, 0, 19)).setTextureSize(128, 64);
        this.boatSides[2] = (new ModelRenderer(this, 0, 27)).setTextureSize(128, 64);
        this.boatSides[3] = (new ModelRenderer(this, 0, 35)).setTextureSize(128, 64);
        this.boatSides[4] = (new ModelRenderer(this, 0, 43)).setTextureSize(128, 64);
        this.boatSides[0].addBox(-14.0F, -9.0F, -3.0F, 28, 16, 3, 0.0F);
        this.boatSides[0].setRotationPoint(0.0F, 3.0F, 1.0F);
        this.boatSides[1].addBox(-13.0F, -7.0F, -1.0F, 18, 6, 2, 0.0F);
        this.boatSides[1].setRotationPoint(-15.0F, 4.0F, 4.0F);
        this.boatSides[2].addBox(-8.0F, -7.0F, -1.0F, 16, 6, 2, 0.0F);
        this.boatSides[2].setRotationPoint(15.0F, 4.0F, 0.0F);
        this.boatSides[3].addBox(-14.0F, -7.0F, -1.0F, 28, 6, 2, 0.0F);
        this.boatSides[3].setRotationPoint(0.0F, 4.0F, -9.0F);
        this.boatSides[4].addBox(-14.0F, -7.0F, -1.0F, 28, 6, 2, 0.0F);
        this.boatSides[4].setRotationPoint(0.0F, 4.0F, 9.0F);
        this.boatSides[0].rotateAngleX = ((float)Math.PI / 2F);
        this.boatSides[1].rotateAngleY = ((float)Math.PI * 3F / 2F);
        this.boatSides[2].rotateAngleY = ((float)Math.PI / 2F);
        this.boatSides[3].rotateAngleY = (float)Math.PI;
        this.paddles[0] = this.makePaddle(true);
        this.paddles[0].setRotationPoint(3.0F, -5.0F, 9.0F);
        this.paddles[1] = this.makePaddle(false);
        this.paddles[1].setRotationPoint(3.0F, -5.0F, -9.0F);
        this.paddles[1].rotateAngleY = (float)Math.PI;
        this.paddles[0].rotateAngleZ = 0.19634955F;
        this.paddles[1].rotateAngleZ = 0.19634955F;
        this.noWater = (new ModelRenderer(this, 0, 0)).setTextureSize(128, 64);
        this.noWater.addBox(-14.0F, -9.0F, -3.0F, 28, 16, 3, 0.0F);
        this.noWater.setRotationPoint(0.0F, -3.0F, 1.0F);
        this.noWater.rotateAngleX = ((float)Math.PI / 2F);
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
        EntityVuzineBoat entityboat = (EntityVuzineBoat)entityIn;
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);

        for (int i = 0; i < 5; ++i)
        {
            this.boatSides[i].render(scale);
        }

        this.renderPaddle(entityboat, 0, scale, limbSwing);
        this.renderPaddle(entityboat, 1, scale, limbSwing);
    }

    public void renderMultipass(Entity p_187054_1_, float p_187054_2_, float p_187054_3_, float p_187054_4_, float p_187054_5_, float p_187054_6_, float scale)
    {
        GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.colorMask(false, false, false, false);
        this.noWater.render(scale);
        GlStateManager.colorMask(true, true, true, true);
    }

    protected ModelRenderer makePaddle(boolean p_187056_1_)
    {
        ModelRenderer modelrenderer = (new ModelRenderer(this, 62, p_187056_1_ ? 0 : 20)).setTextureSize(128, 64);
        modelrenderer.addBox(-1.0F, 0.0F, -5.0F, 2, 2, 18);
        modelrenderer.addBox(p_187056_1_ ? -1.001F : 0.001F, -3.0F, 8.0F, 1, 6, 7);
        return modelrenderer;
    }

    protected void renderPaddle(EntityVuzineBoat boat, int paddle, float scale, float limbSwing)
    {
        float f = boat.getRowingTime(paddle, limbSwing);
        ModelRenderer modelrenderer = this.paddles[paddle];
        modelrenderer.rotateAngleX = (float)MathHelper.clampedLerp(-1.0471975803375244D, -0.2617993950843811D, (double)((MathHelper.sin(-f) + 1.0F) / 2.0F));
        modelrenderer.rotateAngleY = (float)MathHelper.clampedLerp(-(Math.PI / 4D), (Math.PI / 4D), (double)((MathHelper.sin(-f + 1.0F) + 1.0F) / 2.0F));

        if (paddle == 1)
        {
            modelrenderer.rotateAngleY = (float)Math.PI - modelrenderer.rotateAngleY;
        }

        modelrenderer.render(scale);
    }
}
