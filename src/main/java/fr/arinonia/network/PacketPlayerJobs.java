package fr.arinonia.network;

import fr.arinonia.jobs.JobsPlayer;
import fr.arinonia.jobs.JobsRegister;
import fr.arinonia.player.PlayerJobsSaver;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PacketPlayerJobs extends Packet {
	
	private List<JobsPlayer> jobs;
	
	public PacketPlayerJobs() {
	}
	
	public PacketPlayerJobs(PlayerJobsSaver infos) {
		this.setJobs(infos.getJobs());
	}
	
	@Override
	public void readPacketData(PacketBuffer buffer) throws IOException {
		int b = buffer.readInt();
		this.jobs = new ArrayList<JobsPlayer>();
		for (int o = 0; o < b; o++) {
			int id = buffer.readInt();
			int levelJob = buffer.readInt();
			int xpJob = buffer.readInt();
			JobsPlayer jPlayer = new JobsPlayer(JobsRegister.getJobsById(id), levelJob, xpJob);
			this.jobs.add(jPlayer);
		}
	}

	@Override
	public void writePacketData(PacketBuffer buffer) throws IOException {
		buffer.writeInt(this.jobs.size());
		for (JobsPlayer j : this.jobs) {
			int idJobs = j.getJobs().getId();
			buffer.writeInt(idJobs);
			buffer.writeInt(j.getLevel());
			buffer.writeInt(j.getXp());
		}
	}

	@Override
	public void processPacket(INetHandler handle) {
		this.processPacket((INetHandlerPlayClient) handle);
	}
	
	public void processPacket(INetHandlerPlayClient p_148833_1_) {
		p_148833_1_.handlePlayerJobs(this);
	}

	public List<JobsPlayer> getJobs() {
		return jobs;
	}

	public void setJobs(List<JobsPlayer> jobs) {
		this.jobs = jobs;
	}

}
