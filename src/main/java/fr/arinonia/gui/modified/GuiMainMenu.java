package fr.arinonia.gui.modified;

import fr.arinonia.gui.GuiTexturedButton;
import fr.arinonia.utils.Constants;
import net.minecraft.client.gui.*;

import net.minecraft.client.multiplayer.GuiConnecting;

public class GuiMainMenu extends GuiScreen implements GuiYesNoCallback {

    @Override
    public void initGui() {
        int var3 = this.height / 4 + 28;
        this.buttonList.add(new GuiTexturedButton(35, this.width / 2 - 90, var3 - 75, 180, 30, "Local", 3));
        this.buttonList.add(new GuiTexturedButton(19, this.width / 2 - 90, var3, 180, 30, "Rejoindre " + Constants.APP_NAME, 3));
        this.buttonList.add(new GuiTexturedButton(1, this.width / 2 - 90, var3 + 35, 180, 30, "Solo", 1));
        this.buttonList.add(new GuiTexturedButton(0, this.width / 2 - 90, var3 + 70, 180, 30, "Options", 2));
        this.buttonList.add(new GuiTexturedButton(4, this.width / 2 - 90, var3 + 105, 180, 30, "Quitter", 3));

    }

    @Override
    protected void actionPerformed(final GuiButton button) {
        if (button.id == 0) {
            this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
        } else if (button.id == 1) {
            this.mc.displayGuiScreen(new GuiSelectWorld(this));
        } else if (button.id == 2) {
            this.mc.displayGuiScreen(new GuiMultiplayer(this));
        } else if (button.id == 4) {
            this.mc.shutdown();
        }

//        else  if (p_146284_1_.id == 19) {
//                this.mc.displayGuiScreen(new GuiLoadingScreen());
//        }

        else if (button.id == 19) {
            this.mc.displayGuiScreen(new GuiConnecting(this, this.mc, Constants.IP_SERVER, Constants.PORT_SERVER));
        } else if (button.id == 35) {
            this.mc.displayGuiScreen(new GuiConnecting(this, this.mc, "127.0.0.1", 25565));
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground(mouseX, mouseY);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

}
