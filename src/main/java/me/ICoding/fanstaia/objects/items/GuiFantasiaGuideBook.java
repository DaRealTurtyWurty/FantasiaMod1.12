package me.ICoding.fanstaia.objects.items;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import me.ICoding.fanstaia.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

public class GuiFantasiaGuideBook extends GuiScreen 
{
	public static int pageNum = 1;
	public static String pageFile =  Integer.toString(pageNum);
	public int maxPages = 10;
	private static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MOD_ID + ":textures/gui/book_pages/fantasia_guide_page_blank.png");

	private ItemStack stack;
	private GuiButton buttonDone;
	private final int bookImageHeight = 180;
	private final int bookImageWidth = 146;

    private Minecraft mc = Minecraft.getMinecraft();
	private RenderItem renderer = mc.getRenderItem();
    private TextureManager manager = mc.getTextureManager();
    
    public GuiFantasiaGuideBook(ItemStack heldItem) 
    {
		this.stack = heldItem;
	}
	
	public void updateScreen() 
	{
		if(pageNum < 0) pageNum = 1;
		else if(pageNum > maxPages) pageNum = maxPages;
	}
	
	@Override
    public boolean doesGuiPauseGame()
    {
        return false;
    }

	
	@Override
	public void initGui() 
	{
		NBTTagCompound nbt = stack.getTagCompound();
		if(nbt == null) 
		{
		    nbt = new NBTTagCompound();
		}
		
		nbt = nbt.getCompoundTag("Page Number");
		if(nbt.hasKey("Page Number")) 
		{
		    pageNum = nbt.getInteger("Page Number");
		}
		
	 	buttonList.clear();
        Keyboard.enableRepeatEvents(true);
		buttonDone = new GuiButton(0, width / 2 - 50, 100 + bookImageHeight, 98, 20, I18n.format("gui.done", new Object[0])) 
		{
        	@Override
        	public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) 
        	{
        		GL11.glColor4f(7.0F, 1.0F, 1.0F, 1.0F);
        		super.drawButton(mc, mouseX, mouseY, partialTicks);
            }
        	
        	@Override
        	public void playPressSound(SoundHandler soundHandlerIn) 
        	{
                //soundHandlerIn.playSound(PositionedSoundRecord.getMasterRecord(ModSounds.BOOK_CLOSE, 1.0F));
            }
        };
        
        buttonList.add(buttonDone);
	}
	
	public void nextPage() 
	{
		if(pageNum == maxPages) return;
		pageNum++;
	}
	
	public void prevPage() 
	{
		if(pageNum == 1) return;
		pageNum--;
	}
	
	public void setPage(int page) 
	{
		if(page > maxPages) page = maxPages;
		if(page < 1) page = 1;
		pageNum = page;
	}
	
	@Override
	public void drawScreen(int parX, int parY, float partial) 
	{
	    FontRenderer fr = this.fontRenderer;
    	int GUIxCoord = (width/2)-(bookImageWidth/2);
    	int GUIyCoord = (height/2)-(bookImageHeight/2);
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		manager.bindTexture(TEXTURES);
		this.drawTexturedModalRect(GUIxCoord, GUIyCoord, 20, 1, 146, 180);
		
		//Element Util
		String[] typeStringsE = new String[10];
		typeStringsE[0] = " Normal ";
		typeStringsE[1] = TextFormatting.ITALIC + " Light ";
		typeStringsE[2] = TextFormatting.DARK_PURPLE + " Dark ";
		typeStringsE[3] = TextFormatting.DARK_RED + " Fighting ";
		typeStringsE[4] = TextFormatting.YELLOW + " Electric ";
		typeStringsE[5] = TextFormatting.WHITE + " Ice ";
		typeStringsE[6] = TextFormatting.LIGHT_PURPLE + " Poison ";
		typeStringsE[7] = TextFormatting.GRAY + " Ghost ";
		
		//Page 1
		if(pageNum == 1) 
		{
			//Text
			fr.drawString("CryoAddons", GUIxCoord + 15, GUIyCoord + 16, 14489, false);
			fr.drawString("Index:", GUIxCoord + 15, GUIyCoord + 28, 14489, false);
			fr.drawString("-Cryosteel", GUIxCoord + 15, GUIyCoord + 52, 14489, false);
			fr.drawString("-Tools", GUIxCoord + 15, GUIyCoord + 64, 14489, false);
			fr.drawString("-Armor", GUIxCoord + 15, GUIyCoord + 76, 14489, false);
			fr.drawString("-Machines", GUIxCoord + 15, GUIyCoord + 88, 14489, false);
			fr.drawString("-WorldGen", GUIxCoord + 15, GUIyCoord + 100, 14489, false);
			fr.drawString("-Other", GUIxCoord + 15, GUIyCoord + 113, 14489, false);
		}
		
		//Page 2
		else if(pageNum == 2) 
		{
			//Text
			fr.drawString("           Yesterday", GUIxCoord + 15, GUIyCoord + 16,  14489, false);
			fr.drawString("           evening, while", GUIxCoord + 15, GUIyCoord + 28,  14489, false);
			fr.drawString("           out mining I", GUIxCoord + 15, GUIyCoord + 40,  14489, false);
			fr.drawString("discovered this", GUIxCoord + 15, GUIyCoord + 52,  14489, false);
			fr.drawString("strange mineral. It was", GUIxCoord + 15, GUIyCoord + 64,  14489, false);
			fr.drawString("cold to the touch and", GUIxCoord + 15, GUIyCoord + 76,  14489, false);
			fr.drawString("I took a little sample", GUIxCoord + 15, GUIyCoord + 88,  14489, false);
			fr.drawString("back to my cabin with", GUIxCoord + 15, GUIyCoord + 100, 14489, false);
			fr.drawString("me. I heated the", GUIxCoord + 15, GUIyCoord + 112, 14489, false);
			fr.drawString("strange ore", GUIxCoord + 15, GUIyCoord + 124, 14489, false);
			fr.drawString("and forged it", GUIxCoord + 15, GUIyCoord + 136, 14489, false);
			fr.drawString("into an ingot.", GUIxCoord + 15, GUIyCoord + 148, 14489, false);
			fr.drawString("", GUIxCoord + 900000, GUIyCoord + 900000, 16777215, false);
			 
			 manager.bindTexture(TEXTURES);
			 this.drawTexturedModalRect(GUIxCoord+16, GUIyCoord+13,173,2,38,38);
			 this.drawTexturedModalRect(GUIxCoord+87, GUIyCoord+125,173,2,38,38);
		}
		
		if(pageNum == 3) 
		{
		     fr.drawString("           I shall call", GUIxCoord + 15, GUIyCoord + 16,  14489, false);
			 fr.drawString("           this peculiar", GUIxCoord + 15, GUIyCoord + 28,  14489, false);
			 fr.drawString("           metal", GUIxCoord + 15, GUIyCoord + 40,  14489, false);
			 fr.drawString("Cryosteel! Later that", GUIxCoord + 15, GUIyCoord + 52,  14489, false);
			 fr.drawString("day I went back to", GUIxCoord + 15, GUIyCoord + 64,  14489, false);
			 fr.drawString("collect more Cryosteel", GUIxCoord + 15, GUIyCoord + 76,  14489, false);
			 fr.drawString("and obtained a lot", GUIxCoord + 15, GUIyCoord + 88,  14489, false);
			 fr.drawString("more. I came back to", GUIxCoord + 15, GUIyCoord + 100, 14489, false);
			 fr.drawString("my cabin and put it", GUIxCoord + 15, GUIyCoord + 112, 14489, false);
			 fr.drawString("together to", GUIxCoord + 15, GUIyCoord + 124, 14489, false);
			 fr.drawString("form a Block", GUIxCoord + 15, GUIyCoord + 136, 14489, false);
			 fr.drawString("of Cryosteel", GUIxCoord + 15, GUIyCoord + 148, 14489, false);
			 fr.drawString("", GUIxCoord + 900000, GUIyCoord + 900000, 16777215, false);
			 
			 manager.bindTexture(TEXTURES);
			 this.drawTexturedModalRect(GUIxCoord+16, GUIyCoord+13,173,2,38,38);
			 this.drawTexturedModalRect(GUIxCoord+87, GUIyCoord+125,173,2,38,38);
		}
	}
}
