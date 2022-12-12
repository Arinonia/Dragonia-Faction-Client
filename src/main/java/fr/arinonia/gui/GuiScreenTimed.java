package fr.arinonia.gui;

import net.minecraft.client.gui.GuiScreen;

public class GuiScreenTimed extends GuiScreen {

    protected long lastDraw = -1L;
    protected long delta = -1L;
    protected long currentTime = -1L;

    protected void enterDraw() {
        currentTime = System.currentTimeMillis();
        if(lastDraw < 0L)
            lastDraw = currentTime;
        delta = currentTime - lastDraw;
    }

    protected void exitDraw() {
        lastDraw = currentTime;
    }


    @Override
    protected void keyTyped(char typedChar, int keyCode) {
    }

}