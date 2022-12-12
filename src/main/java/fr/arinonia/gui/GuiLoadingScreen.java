package fr.arinonia.gui;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicInteger;

import fr.arinonia.gui.modified.GuiMainMenu;
import fr.arinonia.utils.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;

import io.netty.util.concurrent.GenericFutureListener;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.network.NetHandlerLoginClient;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.handshake.client.C00Handshake;
import net.minecraft.network.login.client.C00PacketLoginStart;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

public class GuiLoadingScreen extends GuiScreen {
    private NetworkManager field_146371_g;
    private static final AtomicInteger field_146372_a = new AtomicInteger(0);
    private static final Logger logger = LogManager.getLogger();
    private final ResourceLocation backGround = new ResourceLocation("textures/gui/dragonia/wallpaper.png");
    private int updateCounter = 0;
    String chargementText = EnumChatFormatting.GREEN + "Connexion en cours ...";
    String serverName = EnumChatFormatting.GOLD + "Serveur:" + EnumChatFormatting.GRAY + " " + Constants.APP_NAME;
    private static String texteHaut = EnumChatFormatting.AQUA + "Préparation a la connexion";
    private static String texteErreur = "";
    private static String texteErreur2 = "";

    private boolean field_146373_h;
    private boolean canConnect = false;
    private boolean error = false;

    public GuiLoadingScreen() {
        texteHaut = EnumChatFormatting.AQUA + "Préparation a la connexion";
        texteErreur = "";
        texteErreur2 = "";

    }

    public void initGui() {
        this.buttonList.add(new GuiButton(9, this.width - 100, this.height - 30, 60, 20, "Annuler"));
    }

    public void updateScreen() {
        if (this.updateCounter != 100) {
            this.updateCounter += 2;
            canConnect = true;
        } else {
            if (canConnect) {
                Minecraft.getMinecraft().loadWorld((WorldClient) null);
                this.texteHaut = EnumChatFormatting.AQUA + "Téléchargement dans le monde de " + Constants.APP_NAME;
                this.connect("127.0.0.1", 25565);
                //this.connect("31.36.182.199", 25565);

            }
            canConnect = false;
        }

        if (this.field_146371_g != null) {
            if (this.field_146371_g.isChannelOpen()) {
                this.field_146371_g.processReceivedPackets();
            } else if (this.field_146371_g.getExitMessage() != null) {
                this.field_146371_g.getNetHandler().onDisconnect(this.field_146371_g.getExitMessage());
            }
        }
    }

    private void connect(final String p_146367_1_, final int p_146367_2_) {
        logger.info("Connexion a " + Constants.APP_NAME);
        (new Thread("Server Connector #" + field_146372_a.incrementAndGet()) {

            public void run() {
                InetAddress var1 = null;

                try {
                    if (GuiLoadingScreen.this.field_146373_h) {
                        return;
                    }

                    var1 = InetAddress.getByName(p_146367_1_);
                    GuiLoadingScreen.this.field_146371_g = NetworkManager.provideLanClient(var1, p_146367_2_);
                    GuiLoadingScreen.this.field_146371_g.setNetHandler(new NetHandlerLoginClient(GuiLoadingScreen.this.field_146371_g, GuiLoadingScreen.this.mc, new GuiMainMenu()));
                    GuiLoadingScreen.this.field_146371_g.scheduleOutboundPacket(new C00Handshake(5, p_146367_1_, p_146367_2_, EnumConnectionState.LOGIN), new GenericFutureListener[0]);
                    GuiLoadingScreen.this.field_146371_g.scheduleOutboundPacket(new C00PacketLoginStart(GuiLoadingScreen.this.mc.getSession().func_148256_e()), new GenericFutureListener[0]);
                    GuiLoadingScreen.texteHaut = EnumChatFormatting.AQUA + "Arrivé sur le serveur";
                } catch (UnknownHostException var5) {
                    if (GuiLoadingScreen.this.field_146373_h) {
                        return;
                    }

                    GuiLoadingScreen.logger.error("Couldn\'t connect to server", var5);
                    GuiLoadingScreen.texteHaut = "";
                    GuiLoadingScreen.texteErreur = EnumChatFormatting.RED + "Aie, Une erreur est survenue";
                    GuiLoadingScreen.texteErreur2 = EnumChatFormatting.RED + "Patiente un moment, si le serveur n'ouvre pas, viens nous voir sur discord";
                    this.interrupt();
                } catch (Exception var6) {
                    if (GuiLoadingScreen.this.field_146373_h) {
                        return;
                    }

                    GuiLoadingScreen.logger.error("Couldn\'t connect to server", var6);
                    this.interrupt();
                    GuiLoadingScreen.texteHaut = "";
                    GuiLoadingScreen.texteErreur = EnumChatFormatting.RED + "Aie, Une erreur est survenue";
                    GuiLoadingScreen.texteErreur2 = EnumChatFormatting.RED + "Patiente un moment, si le serveur n'ouvre pas, viens nous voir sur discord";
                    String var3 = var6.toString();

                    if (var1 != null) {
                        String var4 = var1.toString() + ":" + p_146367_2_;
                        var3 = var3.replaceAll(var4, "");
                    }

                }
            }
        }).start();
    }

    public void drawBack() {
        GL11.glViewport(0, 0, 256, 256);
        this.mc.getTextureManager().bindTexture(this.backGround);
        GL11.glViewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        GL11.glTexParameteri(3553, 10241, 9729);
        GL11.glTexParameteri(3553, 10240, 9729);
        tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, 1.0F);
        int k = this.width;
        int l = this.height;
        tessellator.addVertexWithUV(0.0D, 0.0D, this.zLevel, 0.0D, 0.0D);
        tessellator.addVertexWithUV(0.0D, l, this.zLevel, 0.0D, 1.0D);
        tessellator.addVertexWithUV(k, l, this.zLevel, 1.0D, 1.0D);
        tessellator.addVertexWithUV(k, 0.0D, this.zLevel, 1.0D, 0.0D);
        tessellator.draw();
    }

    public void drawScreen(int par1, int par2, float par3) {
        drawBack();

        drawHorizontalLine(0, this.width, this.height - 47, 1426063360);
        drawHorizontalLine(0, this.width, this.height - 46, 1426063360);
        drawRect(0, this.height - 45, this.width, this.height, 1426063360);

        drawRect(this.width / 2 - 101, this.height - 28, this.width / 2 + 101, this.height - 15, 489386);
        drawRect(this.width / 2 - 100, this.height - 27, this.width / 2 + 100, this.height - 16, 2014243904);

        drawRect(this.width / 2 - 100, this.height - 27, this.width / 2 - 100 + this.updateCounter * 2, this.height - 16, -199510730);

        String percent = EnumChatFormatting.GREEN + "" + this.updateCounter + EnumChatFormatting.GREEN + "%";
        drawCenteredString(this.fontRendererObj, percent, this.width / 2, this.height - 10, 16777215);

        drawCenteredString(this.fontRendererObj, this.texteHaut, this.width / 2, 2, -1);
        drawCenteredString(this.fontRendererObj, this.texteErreur, this.width / 2, this.height / 2, -1);
        drawCenteredString(this.fontRendererObj, this.texteErreur2, this.width / 2, this.height / 2 + 12, -1);

        super.drawScreen(par1, par2, par3);
    }

    public void actionPerformed(GuiButton button) {
        if (button.id == 9) {
            this.field_146373_h = true;

            if (this.field_146371_g != null) {
                this.field_146371_g.closeChannel(new ChatComponentText("Aborted"));
            }
            this.mc.displayGuiScreen(new GuiFadeInOut(this, new GuiMainMenu(), 1000, 0));

        }
    }
}
