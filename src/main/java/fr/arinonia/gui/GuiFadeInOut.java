package fr.arinonia.gui;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;

public class GuiFadeInOut extends GuiScreenTimed {

    private GuiScreen from = null;
    private GuiScreen to;
    private final long duration;
    private final int color;

    private long start = -1L;

    public GuiFadeInOut(GuiScreen from, GuiScreen to, long duration, int color) {
        this.from = from;
        this.to = to;
        this.duration = duration;
        this.color = color & 0xFFFFFF;
    }

    public GuiFadeInOut(GuiScreen to, long duration, int color) {
        this.to = to;
        this.duration = duration;
        this.color = color & 0xFFFFFF;
    }

    public void setGUIs(GuiScreen from, GuiScreen to) {
        this.to = to;
        this.from = from;
    }

    @Override
    public void initGui() {
        this.to.setWorldAndResolution(this.mc, this.width, this.height);
        this.to.initGui();
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) {
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        enterDraw();
        if (start < 1L)
            start = currentTime;

        long tDelta = Math.min(currentTime - start, duration);
        double alphaQ = -Math.abs(tDelta * 2.0 / duration - 1) + 1;
        if (tDelta >= duration) {
            this.mc.displayGuiScreen(this.to);
        } else if (tDelta >= duration / 2) {
            this.to.drawScreen(mouseX, mouseY, partialTicks);
        } else {
            if (from != null)
                this.from.drawScreen(mouseX, mouseY, partialTicks);
        }
        Gui.drawRect(0, 0, this.width, this.height, color | (int) (255 * alphaQ) << 24);

        exitDraw();
    }

}