package net.minecraft.network.play.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.arinonia.jobs.Jobs;
import fr.arinonia.jobs.JobsPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S39PacketPlayerAbilities extends Packet
{
    private boolean field_149119_a;
    private boolean field_149117_b;
    private boolean field_149118_c;
    private boolean field_149115_d;
    private float field_149116_e;
    private float field_149114_f;

    private List<JobsPlayer> jobs;

    public S39PacketPlayerAbilities() {}

    public S39PacketPlayerAbilities(PlayerCapabilities cap)
    {
        this.func_149108_a(cap.disableDamage);
        this.func_149102_b(cap.isFlying);
        this.func_149109_c(cap.allowFlying);
        this.func_149111_d(cap.isCreativeMode);
        this.func_149104_a(cap.getFlySpeed());
        this.func_149110_b(cap.getWalkSpeed());
        this.setJobs(cap.getJobs());
    }

    /**
     * Reads the raw packet data from the data stream.
     */
    public void readPacketData(PacketBuffer p_148837_1_) throws IOException
    {
        byte var2 = p_148837_1_.readByte();
        this.func_149108_a((var2 & 1) > 0);
        this.func_149102_b((var2 & 2) > 0);
        this.func_149109_c((var2 & 4) > 0);
        this.func_149111_d((var2 & 8) > 0);
        this.func_149104_a(p_148837_1_.readFloat());
        this.func_149110_b(p_148837_1_.readFloat());
        int b = p_148837_1_.readInt();

        this.jobs = new ArrayList<JobsPlayer>();
        for (int o = 0; o < b; o++) {
            int id = p_148837_1_.readInt();
            int levelJob = p_148837_1_.readInt();
            long xpJob = p_148837_1_.readLong();
            final JobsPlayer jPlayer = new JobsPlayer(Jobs.getJobsById(id), levelJob, xpJob);
            this.jobs.add(jPlayer);
        }
    }

    /**
     * Writes the raw packet data to the data stream.
     */
    public void writePacketData(PacketBuffer p_148840_1_) throws IOException
    {
        byte var2 = 0;

        if (this.func_149112_c())
        {
            var2 = (byte)(var2 | 1);
        }

        if (this.func_149106_d())
        {
            var2 = (byte)(var2 | 2);
        }

        if (this.func_149105_e())
        {
            var2 = (byte)(var2 | 4);
        }

        if (this.func_149103_f())
        {
            var2 = (byte)(var2 | 8);
        }

        p_148840_1_.writeByte(var2);
        p_148840_1_.writeFloat(this.field_149116_e);
        p_148840_1_.writeFloat(this.field_149114_f);

        for (final JobsPlayer j : this.jobs) {
            int idJobs = Jobs.getIdFromJobs(j.getJob());
            p_148840_1_.writeInt(idJobs);
            p_148840_1_.writeInt(j.getLevel());
            p_148840_1_.writeLong(j.getXp());
        }
    }

    public void processPacket(INetHandlerPlayClient p_148833_1_)
    {
        p_148833_1_.handlePlayerAbilities(this);
    }

    /**
     * Returns a string formatted as comma separated [field]=[value] values. Used by Minecraft for logging purposes.
     */
    public String serialize()
    {
        return String.format("invuln=%b, flying=%b, canfly=%b, instabuild=%b, flyspeed=%.4f, walkspped=%.4f", new Object[] { Boolean.valueOf(this.func_149112_c()), Boolean.valueOf(this.func_149106_d()), Boolean.valueOf(this.func_149105_e()), Boolean.valueOf(this.func_149103_f()), Float.valueOf(this.func_149101_g()), Float.valueOf(this.func_149107_h()) });
    }

    public boolean func_149112_c()
    {
        return this.field_149119_a;
    }

    public void func_149108_a(boolean p_149108_1_)
    {
        this.field_149119_a = p_149108_1_;
    }

    public boolean func_149106_d()
    {
        return this.field_149117_b;
    }

    public void func_149102_b(boolean p_149102_1_)
    {
        this.field_149117_b = p_149102_1_;
    }

    public boolean func_149105_e()
    {
        return this.field_149118_c;
    }

    public void func_149109_c(boolean p_149109_1_)
    {
        this.field_149118_c = p_149109_1_;
    }

    public boolean func_149103_f()
    {
        return this.field_149115_d;
    }

    public void func_149111_d(boolean p_149111_1_)
    {
        this.field_149115_d = p_149111_1_;
    }

    public float func_149101_g()
    {
        return this.field_149116_e;
    }

    public void func_149104_a(float p_149104_1_)
    {
        this.field_149116_e = p_149104_1_;
    }

    public float func_149107_h()
    {
        return this.field_149114_f;
    }

    public void func_149110_b(float p_149110_1_)
    {
        this.field_149114_f = p_149110_1_;
    }

    public void processPacket(INetHandler p_148833_1_)
    {
        this.processPacket((INetHandlerPlayClient)p_148833_1_);
    }

    public List<JobsPlayer> getJobs() {
        return jobs;
    }

    public void setJobs(List<JobsPlayer> jobs) {
        this.jobs = jobs;
    }
}
