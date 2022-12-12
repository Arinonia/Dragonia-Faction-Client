package fr.arinonia.network;

import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

import java.io.IOException;

public class SCLobby extends Packet {

    @Override
    public void readPacketData(PacketBuffer p_148837_1_) throws IOException {

    }

    @Override
    public void writePacketData(PacketBuffer p_148840_1_) throws IOException {

    }

    @Override
    public void processPacket(INetHandler p_148833_1_) {
        ((INetHandlerPlayClient) p_148833_1_).handleOpenLobby();
    }
}
