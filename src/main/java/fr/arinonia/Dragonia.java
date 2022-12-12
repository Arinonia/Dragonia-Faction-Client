package fr.arinonia;

import fr.arinonia.discord.DiscordUtil;
import fr.arinonia.utils.Colors;

public class Dragonia {

    private static boolean alreadyInit = false;
    private DiscordUtil discordUtil;

    public void init() {
        if (alreadyInit) return;
        this.discordUtil = new DiscordUtil();
        new Colors();

        alreadyInit = true;
    }

    public DiscordUtil getDiscordUtil() {
        return discordUtil;
    }
}
