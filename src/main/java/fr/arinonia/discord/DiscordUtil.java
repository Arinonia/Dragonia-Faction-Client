package fr.arinonia.discord;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import net.minecraft.client.Minecraft;

public class DiscordUtil {

    private final DiscordRPC lib = DiscordRPC.INSTANCE;
    private final DiscordRichPresence presence = new DiscordRichPresence();

    public DiscordUtil() {
        final DiscordEventHandlers handlers = new DiscordEventHandlers();
        this.lib.Discord_Initialize("867022097338204191", handlers, true, "");
        this.presence.startTimestamp = System.currentTimeMillis() / 1000;
        this.presence.details = "Lancement du jeu";
        this.presence.state = Minecraft.getMinecraft().getSession().getUsername();
        this.presence.largeImageKey = "logo";
        this.presence.largeImageText = "Un gros dragon";
        this.presence.smallImageKey = "icon";
        this.presence.smallImageText = "Dragonia > all";
        this.lib.Discord_UpdatePresence(this.presence);

        new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                lib.Discord_RunCallbacks();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ignored){}
            }
        }, "RPC-Callback-Handler").start();
    }
    public void setDetails(final String details) {
        this.presence.details = details;
        this.lib.Discord_UpdatePresence(this.presence);
    }

    public void setState(final String state) {
        this.presence.state = state;
        this.lib.Discord_UpdatePresence(this.presence);
    }
    public void setLargeImage(final String key) {
        this.presence.largeImageKey = key;
        this.lib.Discord_UpdatePresence(this.presence);
    }
    public void setLargeImageText(final String text) {
        this.presence.largeImageText = text;
        this.lib.Discord_UpdatePresence(this.presence);
    }
    public void setSmallImage(final String key) {
        this.presence.smallImageKey = key;
        this.lib.Discord_UpdatePresence(this.presence);
    }
    public void setSmallImageText(final String text) {
        this.presence.smallImageText = text;
        this.lib.Discord_UpdatePresence(this.presence);
    }

}
