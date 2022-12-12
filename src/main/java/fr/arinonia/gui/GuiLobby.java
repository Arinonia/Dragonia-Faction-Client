package fr.arinonia.gui;

import fr.arinonia.network.CSSwitchServer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiLobby extends GuiScreen {

    private final ResourceLocation resourceLocation = new ResourceLocation("textures/gui/lobby.png");


    public GuiLobby() {

    }

    @Override
    public void initGui() {
        int var3 = this.height / 4 + 28;

        this.buttonList.add(new GuiTexturedButton(19, this.width / 2 - 90, var3, 180, 30, "Minage", 1));
        this.buttonList.add(new GuiTexturedButton(1, this.width / 2 - 90, var3 + 35, 180, 30, "Faction", 2));

        super.initGui();
    }

    @Override
    public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
        this.drawBack();
        super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
    }

    @Override
    protected void actionPerformed(final GuiButton button) {
        if (button.enabled) {
            switch (button.id) {
                case 0:
                    this.mc.thePlayer.sendQueue.addToSendQueue(new CSSwitchServer("faction"));
                    break;
                case 1:
                    this.mc.thePlayer.sendQueue.addToSendQueue(new CSSwitchServer("minage"));
                    break;
                case 2:

                    break;
            }
        }
    }

    public void drawBack() {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(resourceLocation);
        this.drawTextureWithOptionalSize(this.width / 8, this.height / 8, 0, 0, this.width - this.width / 4, 200, this.width - this.width / 4, 200);
    }

    public void drawTextureWithOptionalSize(int x, int y, int u, int v, int width, int height, int uSize, int vSize) {
        float scaledX = (float) 1 / uSize;
        float scaledY = (float) 1 / vSize;
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(x, y + height, this.zLevel, (float) (u) * scaledX, (float) (v + height) * scaledY);
        tessellator.addVertexWithUV(x + width, y + height, this.zLevel, (float) (u + width) * scaledX, (float) (v + height) * scaledY);
        tessellator.addVertexWithUV(x + width, y, this.zLevel, (float) (u + width) * scaledX, (float) (v) * scaledY);
        tessellator.addVertexWithUV(x, y, this.zLevel, (float) (u) * scaledX, (float) (v) * scaledY);
        tessellator.draw();
    }
}
