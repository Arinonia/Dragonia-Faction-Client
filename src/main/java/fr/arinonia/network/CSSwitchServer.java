package fr.arinonia.network;

import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;

import java.io.IOException;

public class CSSwitchServer extends Packet {

    private String bungeeName;

    public CSSwitchServer(final String bungeeName) {
        this.bungeeName = bungeeName;
    }

    /**
     * A packet always need an empty constructor !
     */
    public CSSwitchServer() {}

    @Override
    public void readPacketData(final PacketBuffer packetBuffer) throws IOException {}

    @Override
    public void writePacketData(final PacketBuffer packetBuffer) throws IOException {
        packetBuffer.writeStringToBuffer(this.bungeeName != null ? this.bungeeName : "empty");
    }

    @Override
    public void processPacket(final INetHandler packetBuffer) {}

    public String getBungeeName() {
        return this.bungeeName;
    }
}
